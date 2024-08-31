package com.board.login.service;

import com.board.login.controller.dto.LoginRequest;
import com.board.login.service.exception.ExistMemberLoginIdException;
import com.board.member.domain.Member;
import com.board.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtLoginService implements LoginService{

    private final MemberRepository memberRepository;

    @Override
    public Member login(LoginRequest loginRequest) {
        Member member = memberRepository.findByMemberLoginId(loginRequest.memberLoginId()).orElseThrow(ExistMemberLoginIdException::new);
        member.checkPassword(loginRequest.memberPassword());
        return member;
    }
}
