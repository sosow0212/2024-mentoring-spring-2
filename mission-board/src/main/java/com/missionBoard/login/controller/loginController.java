package com.missionBoard.login.controller;

import com.missionBoard.login.dto.JoinRequest;
import com.missionBoard.login.dto.LoginRequest;
import com.missionBoard.login.domain.Member;
import com.missionBoard.login.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class loginController {

    private MemberService service;

    public loginController(MemberService service) {
        this.service = service;
    }

    @PostMapping("/join")
    public ResponseEntity<Void> join(@ModelAttribute JoinRequest joinRequest) {
        service.join(joinRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> login(HttpServletRequest request,  @ModelAttribute LoginRequest loginRequest) {
        Member loginMember = service.login(loginRequest.loginId(), loginRequest.password());
        System.out.println("LoginMember:" + loginMember.getLoginId());
        HttpSession session = request.getSession();
        session.setAttribute("user", loginMember);
        return ResponseEntity.ok().body(loginMember);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok().build();
    }
}
