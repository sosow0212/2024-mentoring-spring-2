package com.board.login.service;

import com.board.login.controller.dto.LoginRequest;
import com.board.login.domain.CookieEntity;
import com.board.login.repository.CookieRepository;
import com.board.login.service.exception.ExistCookieException;
import com.board.login.service.exception.ExistMemberLoginIdException;
import com.board.login.service.exception.ExistMemberPasswordException;
import com.board.member.domain.Member;
import com.board.member.repository.MemberRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Primary
public class CookieLoginService implements LoginService {

    private final MemberRepository memberRepository;
    private final CookieRepository cookieRepository;

    public CookieLoginService(MemberRepository memberRepository, CookieRepository cookieRepository) {
        this.memberRepository = memberRepository;
        this.cookieRepository = cookieRepository;
    }

    @Override
    public String login(HttpServletResponse response, LoginRequest loginRequest) {
        Member member = memberRepository.findByMemLoginId(loginRequest.memLoginId());
        if (member == null) {
            throw new ExistMemberLoginIdException();
        }
        if (!Objects.equals(member.getMemPassword(), loginRequest.memPassword())) {
            throw new ExistMemberPasswordException();
        }
        Cookie createdCookie = createCookie(response, String.valueOf(member.getId()));
        saveCookie(createdCookie.getName(), createdCookie.getValue());
        return member.getMemNickName();
    }

    @Override
    public void logout(HttpServletResponse response, HttpServletRequest request) {
        deleteCookie(response);
        removeCookie(request);
    }

    private Cookie createCookie(HttpServletResponse response, String cookieContent) {
        Cookie cookieCreate = new Cookie("memberId", cookieContent);
        cookieCreate.setMaxAge(60 * 60);
        cookieCreate.setPath("/board");
        response.addCookie(cookieCreate);
        return cookieCreate;
    }

    private void deleteCookie(HttpServletResponse response) {
        Cookie cookieRemove = new Cookie("memberId", "");
        cookieRemove.setMaxAge(0);
        cookieRemove.setPath("/board");
        response.addCookie(cookieRemove);
    }

    private void saveCookie(String cookieName, String cookieContent) {
        CookieEntity cookieEntity = new CookieEntity(cookieName, cookieContent);
        cookieRepository.save(cookieEntity);
    }

    private void removeCookie(HttpServletRequest request) {
        CookieEntity cookie = getCookie(request, "memberId");
        cookieRepository.delete(cookie);
    }

    public CookieEntity getCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (Objects.equals(cookie.getName(), cookieName)) {
                return cookieRepository.findByCookieName(cookieName);
            }
        }
        throw new ExistCookieException();
    }
}
