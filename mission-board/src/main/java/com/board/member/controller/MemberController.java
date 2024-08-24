package com.board.member.controller;

import com.board.login.annotation.Login;
import com.board.member.controller.dto.CreateRequest;
import com.board.member.controller.dto.CreateResponse;
import com.board.member.domain.Member;
import com.board.member.mapper.MemberMapper;
import com.board.member.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/members")
    public ResponseEntity<Void> createUser(@RequestBody CreateRequest createRequest) {
        CreateResponse createResponse = MemberMapper.toCreateResponse(memberService.signUp(createRequest));
        URI location = URI.create("/board/members/" + createResponse.id());
        log.info("{}님 회원가입 성공.", createResponse.memberNickName());
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/test")
    public void dd(@Login Member member){
        log.info(member.getMemberName());
    }
}
