package com.lotto.web.lotto.service;

import com.lotto.web.lotto.dto.LottoRequest;
import com.lotto.web.lotto.dto.exception.NotFoundLottoException;
import com.lotto.web.lotto.repository.LottoAnswerRepository;
import com.lotto.web.lotto.repository.LottoRepository;
import com.lotto.web.member.entity.Member;
import com.lotto.web.member.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class LottoServiceTest {

    @Autowired
    private LottoAnswerRepository lottoAnswerRepository;
    @Autowired
    private LottoService lottoService;
    @Autowired
    private LottoRepository lottoRepository;
    @Autowired
    private MemberRepository memberRepository;
    private Member member;

    @BeforeEach
    void setup() {
        member = new Member("jay", 5000);
        memberRepository.save(member);
    }

    @Test
    @Transactional(readOnly = true)
    @DisplayName("Lotto를 구매한다.")
    void butLotto() {
        // given
        LottoRequest lottoRequest = new LottoRequest(member.getId(), 3);
        lottoService.buyLotto(lottoRequest);
        int expectedLottoSize = 3;
        int expectedLottoAnswerSize = 1;

        // when
        int actualLottoSize = lottoRepository.findAllByMemberId(member.getId()).size();
        int actualLottoAnswerSize = lottoAnswerRepository.findAll().size();

        // then
        assertEquals(expectedLottoSize, actualLottoSize);
        assertEquals(expectedLottoAnswerSize, actualLottoAnswerSize);
    }

    @Test
    @Transactional(readOnly = true)
    @DisplayName("Lotto를 잘못된 userId로 조회한다.")
    void getLottos_exception() {
        // given
        String expected = "로또 없음.";

        // when
        NotFoundLottoException actual = assertThrows(NotFoundLottoException.class, () -> lottoService.getLottos(2L));

        // then
        assertEquals(expected, actual.getMessage());
    }
}
