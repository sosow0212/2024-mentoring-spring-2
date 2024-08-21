package com.board.login.service;

import com.board.login.controller.dto.LoginRequest;
import com.board.login.service.exception.ExistMemberException;
import com.board.login.service.exception.ExistMemberLoginIdException;
import com.board.login.service.exception.ExistSessionException;
import com.board.member.domain.Member;
import com.board.member.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SessionLoginService implements LoginService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    @Override
    public Member login(LoginRequest loginRequest) {
        Member member = memberRepository.findByMemberLoginId(loginRequest.memberLoginId()).orElseThrow(ExistMemberLoginIdException::new);
        member.checkPassword(loginRequest.memberPassword());
        return member;
    }

    @Transactional(readOnly = true)
    public Member findMemberBySession(HttpSession httpSession) {
        Long memberId = (Long) httpSession.getAttribute("memberId");
        return memberRepository.findById(memberId)
                .orElseThrow(ExistMemberException::new);
    }

    @Transactional(readOnly = true)
    public HttpSession findSession(HttpServletRequest request) {
        return Optional.ofNullable(request.getSession(false))
                .orElseThrow(ExistSessionException::new);
    }

}
