package com.lotto.web.member.controller;

import com.lotto.web.member.dto.CreateRequest;
import com.lotto.web.member.dto.CreatedResponse;
import com.lotto.web.member.dto.MemberResponse;
import com.lotto.web.member.dto.MemberResponses;
import com.lotto.web.member.mapper.MemberMapper;
import com.lotto.web.member.service.MemberService;
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

    @PostMapping("/members")
    public ResponseEntity<Void> createUser(@RequestBody CreateRequest createRequest) {
        CreatedResponse createdResponse = MemberMapper.toCreatedResponse(memberService.createMember(createRequest));
        URI location = URI.create("/api/members/" + createdResponse.id());
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/members")
    public ResponseEntity<MemberResponses> showUsers() {
        List<MemberResponse> memberResponse = memberService.findAllUsers().stream()
                .map(MemberMapper::toMemberResponse)
                .toList();
        MemberResponses memberResponses = MemberMapper.toMemberResponses(memberResponse);
        return ResponseEntity.ok(memberResponses);
    }
}
