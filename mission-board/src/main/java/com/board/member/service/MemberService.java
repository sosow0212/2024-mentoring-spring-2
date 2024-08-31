package com.board.member.service;

import com.board.member.controller.dto.MemberRequest;
import com.board.member.domain.Member;
import com.board.member.mapper.MemberMapper;
import com.board.member.repository.MemberRepository;
import com.board.member.service.exception.ExistMemberException;
import com.board.member.service.exception.ExistMemberIdException;
import com.board.member.service.exception.ExistMemberNickNameException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public Member signUp(MemberRequest memberRequest) {
        Member member = MemberMapper.toMember(memberRequest);
        checkDuplicateMemberLoginId(member.getMemberLoginId());
        checkDuplicateMemNickName(member.getMemberNickName());
        memberRepository.save(member);
        return member;
    }

    public Member updateMember(MemberRequest memberRequest, Long memberId) {
        checkDuplicateMemberLoginId(memberRequest.memberLoginId());
        checkDuplicateMemNickName(memberRequest.memberNickName());
        Member member = findMember(memberId);
        member.updateMember(member.getId(), memberRequest.memberName(), memberRequest.memberNickName(), memberRequest.memberLoginId(), memberRequest.memberPassword());
        return member;
    }

    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(ExistMemberException::new);
    }

    public Member deleteMember(Long memberId) {
        Member member = findMember(memberId);
        memberRepository.delete(member);
        return member;
    }

    private void checkDuplicateMemberLoginId(String memberId) {
        if (memberRepository.existsByMemberLoginId(memberId)) {
            throw new ExistMemberIdException();
        }
    }

    private void checkDuplicateMemNickName(String memberNickName) {
        if (memberRepository.existsByMemberNickName(memberNickName)) {
            throw new ExistMemberNickNameException();
        }
    }
}
