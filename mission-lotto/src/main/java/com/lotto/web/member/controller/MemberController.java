package com.lotto.web.member.controller;

import com.lotto.web.member.dto.CreateRequest;
import com.lotto.web.member.dto.MemberResponse;
import com.lotto.web.member.entity.Member;
import com.lotto.web.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/users")
    public ResponseEntity<Void> createUser(@RequestBody CreateRequest createRequest){
        Member member = memberService.createUser(createRequest);
        URI location = URI.create("/api/users/"+ member.getId());
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/users")
    public ResponseEntity<List<MemberResponse>> showUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(memberService.getAllUsers());
    }
}
