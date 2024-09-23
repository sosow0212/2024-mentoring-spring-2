package com.board.login.service;

import com.board.login.controller.dto.LoginRequest;
import com.board.member.domain.Member;
import com.board.member.repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpSession;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SessionLoginServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private SessionLoginService sessionLoginService;

    @DisplayName("올바른 로그인 테스트.")
    @Test
    void login(){
        //given
        LoginRequest loginRequest = new LoginRequest("aaa", "bbb");
        Member loginMember = new Member(1L, "jay", "jj", "aaa", "bbb");
        when(memberRepository.findByMemberLoginId(loginRequest.memberLoginId()))
                .thenReturn(Optional.of(loginMember));
        String expected = "aaa";


        //when
        Member member = sessionLoginService.login(loginRequest);

        //then
        assertEquals(expected, member.getMemberLoginId());
    }

    @DisplayName("세션에서 멤버 뽑아내는 테스트.")
    @Test
    void find_member_by_session(){
        //given
        String sessionName = "memberId";
        Long memberId = 1L;
        Member sessionMember = new Member(1L, "jay", "jj", "aaa", "bbb");
        HttpSession httpSession = new MockHttpSession();
        httpSession.setAttribute(sessionName, memberId);
        when(memberRepository.findById(memberId)).thenReturn(Optional.of(sessionMember));

        //when
        Member result = sessionLoginService.findMemberBySession(httpSession);

        //then
        assertEquals(sessionMember, result);
    }
}
