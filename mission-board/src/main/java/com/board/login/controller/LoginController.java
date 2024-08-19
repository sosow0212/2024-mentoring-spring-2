package com.board.login.controller;

import com.board.login.controller.dto.LoginRequest;
import com.board.login.controller.dto.LoginResponse;
import com.board.login.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/board")
@Slf4j
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/members/login")
    public ResponseEntity<Void> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response){
        LoginResponse loginResponse = new LoginResponse(loginService.login(response, loginRequest));
        log.info(loginResponse.memNickName()+"님 로그인 성공.");
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("members/logout")
    public ResponseEntity<Void> logout(HttpServletResponse response, HttpServletRequest request){
        loginService.logout(response, request);
        log.info("로그아웃 성공.");
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
