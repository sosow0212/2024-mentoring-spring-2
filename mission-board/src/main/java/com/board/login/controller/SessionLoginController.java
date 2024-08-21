package com.board.login.controller;

import com.board.login.controller.dto.LoginRequest;
import com.board.login.controller.dto.LoginResponse;
import com.board.login.controller.dto.LogoutResponse;
import com.board.login.domain.SessionStorage;
import com.board.login.service.SessionLoginService;
import com.board.member.mapper.MemberMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
public class SessionLoginController {

    private final SessionLoginService sessionLoginService;

    @PostMapping("/members/session/login")
    public ResponseEntity<Void> login(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        LoginResponse loginResponse = MemberMapper.toLoginResponse(sessionLoginService.login(loginRequest));
        HttpSession session = request.getSession();
        session.setAttribute("memberId", loginResponse.memberId());
        session.setMaxInactiveInterval(1800);
        SessionStorage.setSession(loginResponse.memberId(), session);
        log.info("{}님 로그인 성공.", loginResponse.memberNickName());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/members/session/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request){
        HttpSession httpSession = sessionLoginService.findSession(request);
        LogoutResponse logoutResponse = MemberMapper.toLogoutResponse(sessionLoginService.findMemberBySession(httpSession));
        httpSession.invalidate();
        SessionStorage.deleteSession(logoutResponse.memberId());
        log.info("{}님 로그아웃 성공.", logoutResponse.memberNickName());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
