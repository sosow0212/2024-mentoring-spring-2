package com.board.Member.controller;

import com.board.Member.controller.dto.CreateRequest;
import com.board.Member.controller.dto.CreateResponse;
import com.board.Member.mapper.MemberMapper;
import com.board.Member.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/board")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/members")
    public ResponseEntity<Void> createUser(@RequestBody CreateRequest createRequest) {
        CreateResponse createResponse = MemberMapper.toCreateResponse(memberService.signUp(createRequest));
        URI location = URI.create("/board/members/" + createResponse.id());
        return ResponseEntity.created(location).build();
    }
}
