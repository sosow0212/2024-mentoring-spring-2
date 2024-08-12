package com.lotto.web.lotto.service;

import com.lotto.web.lotto.domain.CreateRandomNumber;
import com.lotto.web.lotto.domain.Lotto;
import com.lotto.web.lotto.domain.LottoNumberParser;
import com.lotto.web.lotto.domain.LottoRank;
import com.lotto.web.lotto.mapper.LottoMapper;
import com.lotto.web.lotto.dto.LottoRequest;
import com.lotto.web.lotto.entity.LottoAnswer;
import com.lotto.web.lotto.entity.LottoEntity;
import com.lotto.web.member.entity.Member;
import com.lotto.web.member.repository.MemberRepository;
import com.lotto.web.member.service.MemberService;
import com.lotto.web.lotto.repository.LottoAnswerRepository;
import com.lotto.web.lotto.repository.LottoRepository;
import com.lotto.web.member.service.exception.NotFoundMemberException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LottoService {

    private final CreateRandomNumber createRandomNumber;
    private final MemberService memberService;
    private final LottoRepository lottoRepository;
    private final LottoAnswerRepository lottoAnswerRepository;
    private final MemberRepository memberRepository;

    public LottoService(CreateRandomNumber createRandomNumber, MemberService memberService, LottoRepository lottoRepository, LottoAnswerRepository lottoAnswerRepository, MemberRepository memberRepository) {
        this.createRandomNumber = createRandomNumber;
        this.memberService = memberService;
        this.lottoRepository = lottoRepository;
        this.lottoAnswerRepository = lottoAnswerRepository;
        this.memberRepository = memberRepository;
    }

    public void buyLotto(LottoRequest lottoRequest) {
        memberService.buyLotto(lottoRequest.userId(), lottoRequest.count());
        LottoAnswer lottoAnswer = getLottoAnswer();
        saveLottos(lottoRequest, lottoAnswer.getLottoAnswer());
    }

    @Transactional(readOnly = true)
    public List<LottoEntity> getLottos(Long id) {
        return lottoRepository.findAllByMemberId(id);
    }

    private void saveLottos(LottoRequest lottoRequest, String lottoAnswer) {
        Member member = memberRepository.findById(lottoRequest.userId())
                .orElseThrow(NotFoundMemberException::new);
        LottoNumberParser parsedLottoAnswer = new LottoNumberParser(lottoAnswer);
        for (int i = 0; i < lottoRequest.count(); i++) {
            Lotto lotto = new Lotto(createRandomNumber);
            int count = getLottoRank(lotto.getLotto(), parsedLottoAnswer.getLottoNumber());
            LottoEntity lottoEntity = LottoMapper.toLottoEntity(member, lotto.getLotto().toString(), win(count));
            lottoRepository.save(lottoEntity);
            memberService.saveWinning(member, count);
        }
    }

    private boolean win(int count) {
        return count >= 3;
    }

    private int getLottoRank(List<Integer> lotto, List<Integer> lottoAnswer) {
        LottoRank lottoRank = new LottoRank(lotto, lottoAnswer);
        return lottoRank.getCount();
    }

    private LottoAnswer getLottoAnswer() {
        return lottoAnswerRepository.findFirstByOrderById().orElseGet(this::createLottoAnswer);
    }

    private LottoAnswer createLottoAnswer() {
        Lotto lotto = new Lotto(createRandomNumber);
        LottoAnswer lottoAnswer = new LottoAnswer(lotto.getLotto().toString());
        lottoAnswerRepository.save(lottoAnswer);
        return lottoAnswer;
    }
}
