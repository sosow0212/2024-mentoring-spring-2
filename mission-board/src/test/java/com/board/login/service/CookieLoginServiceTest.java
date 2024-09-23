package com.board.login.service;

import com.board.login.controller.dto.LoginRequest;
import com.board.member.domain.Member;
import com.board.member.repository.MemberRepository;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CookieLoginServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private CookieLoginService cookieLoginService;

    @DisplayName("올바른 로그인 테스트.")
    @Test
    void login() {
        //given
        LoginRequest loginRequest = new LoginRequest("aaa", "bbb");
        Member loginMember = new Member(1L, "jay", "jj", "aaa", "bbb");
        when(memberRepository.findByMemberLoginId(loginRequest.memberLoginId()))
                .thenReturn(Optional.of(loginMember));
        String expected = "aaa";


        //when
        Member member = cookieLoginService.login(loginRequest);

        //then
        assertEquals(expected, member.getMemberLoginId());
    }

    @DisplayName("쿠키를 통해 멤버를 뽑아내는 테스트.")
    @Test
    void find_member_by_cookie() {
        //given
        String cookieName = "memberId";
        String memberId = "1";
        Long memberIdLong = Long.valueOf(memberId);
        Member cookieMember = new Member(1L, "jay", "jj", "aaa", "bbb");
        Cookie cookie = new Cookie(cookieName, memberId);
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCookies(cookie);
        when(memberRepository.findById(memberIdLong)).thenReturn(Optional.of(cookieMember));

        //when
        Member result = cookieLoginService.findMemberByCookie(request);

        //then
        assertEquals(cookieMember, result);
    }
}
