package com.board.member.controller;

import com.board.login.annotation.Login;
import com.board.member.controller.dto.MemberRequest;
import com.board.member.controller.dto.MemberResponse;
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
    public ResponseEntity<Void> createUser(@RequestBody MemberRequest memberRequest) {
        MemberResponse memberResponse = MemberMapper.toMemberResponse(memberService.signUp(memberRequest));
        URI location = URI.create("/api/members/" + memberResponse.id());
        log.info("{}님 회원가입 성공.", memberResponse.memberNickName());
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/members")
    public ResponseEntity<MemberResponse> showMember(@Login Member member) {
        MemberResponse memberResponse = MemberMapper.toMemberResponse(member);
        log.info("{}님 정보 조회하였습니다.", memberResponse.memberNickName());
        return ResponseEntity.ok(memberResponse);
    }

    @PatchMapping("/members")
    public ResponseEntity<MemberResponse> updateMember(@RequestBody MemberRequest memberRequest, @Login Member member) {
        MemberResponse memberResponse = MemberMapper.toMemberResponse(memberService.updateMember(memberRequest, member));
        log.info("{}님의 회원정보가 수정되었습니다.", memberResponse.memberNickName());
        return ResponseEntity.ok(memberResponse);
    }

    @DeleteMapping("/members")
    public ResponseEntity<MemberResponse> deleteMember(@Login Member member) {
        MemberResponse memberResponse = MemberMapper.toMemberResponse(memberService.deleteMember(member));
        log.info("{}님 탈퇴하였습니다.", memberResponse.memberNickName());
        return ResponseEntity.ok(memberResponse);
    }
}
