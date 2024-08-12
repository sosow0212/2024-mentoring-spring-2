package com.lotto.web.member.service;

import com.lotto.web.member.entity.MemberLotto;
import com.lotto.web.member.repository.MemberLottoRepository;
import com.lotto.web.member.dto.CreateRequest;
import com.lotto.web.member.entity.Member;
import com.lotto.web.member.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberLottoRepository memberLottoRepository;
    private Member member;
    private MemberLotto memberLotto;

    @BeforeEach
    void setUp() {
        member = new Member("jay", 5000);
        memberLotto = new MemberLotto(member);
        memberRepository.save(member);
        memberLottoRepository.save(memberLotto);
    }

    @Test
    @DisplayName("Member를 생성한다.")
    void createMember() {
        // given
        CreateRequest createRequest = new CreateRequest("nana", 5000);
        memberService.createMember(createRequest);
        int expected = 2;

        // when
        int actual = memberRepository.findAll().size();

        // then
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Lotto를 구매하여 member 상태를 업데이트 한다.")
    void buyLotto() {
        // given
        memberService.buyLotto(member.getId(), 3);
        int expectedLottoCount = 3;
        int expectedMoney = 2000;

        // when
        int actualLottoCount = memberLotto.getLottoCount();
        int actualMoney = member.getMoney();

        // then
        assertEquals(expectedLottoCount, actualLottoCount);
        assertEquals(expectedMoney, actualMoney);
    }

    @Test
    @Transactional(readOnly = true)
    @DisplayName("모든 유저를 반환한다.")
    void findAllUsers() {
        // given
        int expected = 1;

        // when
        int actual = memberService.findAllUsers().size();

        // then
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("당첨금을 저장한다.")
    void saveWinning() {
        // given
        memberService.saveWinning(member, 3);
        int expected = 5000;

        // when
        int actual = memberLotto.getWinningPrice();

        // then
        assertEquals(expected, actual);
    }
}
