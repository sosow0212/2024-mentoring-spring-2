package com.board.login.controller;

import com.board.login.controller.dto.LoginRequest;
import com.board.login.controller.dto.LoginResponse;
import com.board.login.controller.dto.LogoutResponse;
import com.board.login.domain.CookieStorage;
import com.board.login.service.CookieLoginService;
import com.board.member.mapper.MemberMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Slf4j
public class CookieLoginController {

    private final CookieLoginService cookieLoginService;

    @PostMapping("/members/cookie/login")
    public ResponseEntity<Void> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        LoginResponse loginResponse = MemberMapper.toLoginResponse(cookieLoginService.login(loginRequest));
        Cookie cookieCreate = new Cookie("memberId", String.valueOf(loginResponse.memberId()));
        cookieCreate.setMaxAge(60 * 60);
        cookieCreate.setPath("/api");
        response.addCookie(cookieCreate);
        CookieStorage.setCookie(loginResponse.memberId(), cookieCreate);
        log.info("{}님 로그인 성공.", loginResponse.memberNickName());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("members/cookie/logout")
    public ResponseEntity<Void> logout(HttpServletResponse response, HttpServletRequest request) {
        LogoutResponse logoutResponse = MemberMapper.toLogoutResponse(cookieLoginService.findMemberByCookie(request));
        Cookie cookieRemove = new Cookie("memberId", "");
        cookieRemove.setMaxAge(0);
        response.addCookie(cookieRemove);
        CookieStorage.deleteCookie(logoutResponse.memberId());
        log.info("{}님 로그아웃 성공.", logoutResponse.memberNickName());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
