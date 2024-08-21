package com.board.member.controller;

import com.board.member.controller.dto.CreateRequest;
import com.board.member.controller.dto.CreateResponse;
import com.board.member.mapper.MemberMapper;
import com.board.member.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/members")
    public ResponseEntity<Void> createUser(@RequestBody CreateRequest createRequest) {
        CreateResponse createResponse = MemberMapper.toCreateResponse(memberService.signUp(createRequest));
        URI location = URI.create("/board/members/" + createResponse.id());
        return ResponseEntity.created(location).build();
    }
}
