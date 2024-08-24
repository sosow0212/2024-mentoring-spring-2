package com.board.login.service;

import com.board.login.controller.dto.LoginRequest;
import com.board.login.service.exception.ExistCookieException;
import com.board.login.service.exception.ExistMemberException;
import com.board.login.service.exception.ExistMemberLoginIdException;
import com.board.member.domain.Member;
import com.board.member.repository.MemberRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CookieLoginService implements LoginService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    @Override
    public Member login(LoginRequest loginRequest) {
        Member member = memberRepository.findByMemberLoginId(loginRequest.memberLoginId()).orElseThrow(ExistMemberLoginIdException::new);
        member.checkPassword(loginRequest.memberPassword());
        return member;
    }

    @Transactional(readOnly = true)
    public Member findMemberByCookie(HttpServletRequest request) {
        String memberId = readCookie(request).orElseThrow(ExistCookieException::new);
        Long realMemberId = Long.valueOf(memberId);
        return memberRepository.findById(realMemberId).orElseThrow(ExistMemberException::new);
    }

    private Optional<String> readCookie(HttpServletRequest request) {
        return Arrays.stream(Optional.ofNullable(request.getCookies())
                        .orElseThrow(ExistCookieException::new))
                .filter(c -> "memberId".equals(c.getName()))
                .map(Cookie::getValue)
                .findAny()
                .orElseThrow(ExistCookieException::new).describeConstable();
    }
}
