package com.missionBoard.login.service;

import com.missionBoard.exception.BadJoinRequestException;
import com.missionBoard.login.dto.JoinRequest;
import com.missionBoard.login.domain.Member;
import com.missionBoard.exception.NotFoundUserException;
import com.missionBoard.login.repository.MemberRepository;
import org.springframework.stereotype.Component;

@Component
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void join(JoinRequest joinRequest) {
        checkIdDuplication(joinRequest);
        Member member = joinRequest.toEntity();
        memberRepository.save(member);
    }

    private void checkIdDuplication(JoinRequest joinRequest) {
        if (memberRepository.existsMemberByLoginId(joinRequest.loginId())){
            throw new BadJoinRequestException();
        }
    }

    public Member login(String loginId, String password) {
        return memberRepository.findByLoginId(loginId)
                .filter(member -> member.getPassword().equals(password))
                .orElseThrow(() -> new NotFoundUserException());
    }
}
