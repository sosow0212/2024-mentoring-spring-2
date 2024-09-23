package com.board.login.controller;

import com.board.login.controller.dto.LoginRequest;
import com.board.login.controller.dto.LoginResponse;
import com.board.login.service.JwtLoginService;
import com.board.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class JwtLoginController {

    private final JwtLoginService jwtLoginService;

    @PostMapping("/members/jwt/login")
    public ResponseEntity<HttpHeaders> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = MemberMapper.toLoginResponse(jwtLoginService.login(loginRequest));
        String jwtToken = jwtLoginService.createJwtToken(loginResponse.memberId());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer " + jwtToken);
        log.info("{}님 로그인 성공.", loginResponse.memberNickName());
        return ResponseEntity.status(HttpStatus.OK).body(httpHeaders);
    }
}
