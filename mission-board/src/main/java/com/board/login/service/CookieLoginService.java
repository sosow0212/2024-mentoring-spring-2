package com.board.login.service;

import com.board.login.controller.dto.LoginRequest;
import com.board.login.service.exception.ExistCookieException;
import com.board.login.service.exception.ExistMemberException;
import com.board.login.service.exception.ExistMemberLoginIdException;
import com.board.member.domain.Member;
import com.board.member.repository.MemberRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
@Primary
public class CookieLoginService implements LoginService {

    private final MemberRepository memberRepository;

    public CookieLoginService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Member login(HttpServletResponse response, LoginRequest loginRequest) {
        Member member = memberRepository.findByMemberLoginId(loginRequest.memberLoginId()).orElseThrow(ExistMemberLoginIdException::new);
        member.checkPassword(loginRequest.memberPassword());
        return member;
    }

    @Override
    public Member logout(HttpServletRequest request) {
        Cookie cookie = getCookie(request);
        Long memberId = Long.valueOf(cookie.getValue());
        return memberRepository.findById(memberId).orElseThrow(ExistMemberException::new);
    }

    private Cookie getCookie(HttpServletRequest request){
        Cookie[] cookies = isValidCookies(request);
        return Arrays.stream(cookies)
                .filter(cookie -> "memberId".equals(cookie.getName()))
                .findFirst()
                .orElseThrow(ExistCookieException::new);
    }

    private Cookie[] isValidCookies(HttpServletRequest request){
        return Optional.ofNullable(request.getCookies()).orElseThrow(ExistCookieException::new);
    }
}
