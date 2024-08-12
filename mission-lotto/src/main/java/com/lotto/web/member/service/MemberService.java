package com.lotto.web.member.service;

import com.lotto.web.lotto.domain.LottoPrice;
import com.lotto.web.member.dto.CreateRequest;
import com.lotto.web.member.entity.Member;
import com.lotto.web.member.entity.MemberLotto;
import com.lotto.web.member.mapper.MemberMapper;
import com.lotto.web.member.repository.MemberLottoRepository;
import com.lotto.web.member.repository.MemberRepository;
import com.lotto.web.member.service.exception.NotFoundMemberException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberLottoRepository memberLottoRepository;

    public MemberService(MemberRepository memberRepository, MemberLottoRepository memberLottoRepository) {
        this.memberRepository = memberRepository;
        this.memberLottoRepository = memberLottoRepository;
    }

    public Member createMember(CreateRequest createRequest) {
        Member member = MemberMapper.toMember(createRequest);
        memberRepository.save(member);
        return member;
    }

    public void buyLotto(Long id, int count) {
        Member member = memberRepository.findById(id)
                .orElseThrow(NotFoundMemberException::new);
        member.updateMoney(count);
        MemberLotto memberLotto = new MemberLotto(member);
        memberLottoRepository.save(memberLotto);
        memberLotto.updateLottoCount(count);
    }

    @Transactional(readOnly = true)
    public List<MemberLotto> findAllUsers() {
        return memberLottoRepository.findAll();
    }

    public void saveWinning(Member member, int count) {
        MemberLotto memberLotto = memberLottoRepository.findById(member.getId())
                .orElseThrow(NotFoundMemberException::new);
        int winning = memberLotto.getWinningPrice() + LottoPrice.getLottoPrice(count);
        memberLotto.updateWinningPrice(winning);
    }
}
