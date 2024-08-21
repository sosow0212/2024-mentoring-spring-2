package com.board.member.service;

import com.board.member.controller.dto.CreateRequest;
import com.board.member.domain.Member;
import com.board.member.mapper.MemberMapper;
import com.board.member.repository.MemberRepository;
import com.board.member.service.exception.ExistMemberIdException;
import com.board.member.service.exception.ExistMemberNickNameException;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member signUp(CreateRequest createRequest){
        Member member = MemberMapper.toMember(createRequest);
        checkDuplicateMemberLoginId(member.getMemberLoginId());
        checkDuplicateMemNickName(member.getMemberNickName());
        memberRepository.save(member);
        return member;
    }

    private void checkDuplicateMemberLoginId(String memId){
        if(memberRepository.existsByMemberLoginId(memId)){
            throw new ExistMemberIdException();
        }
    }

    private void checkDuplicateMemNickName(String memberNickName){
        if(memberRepository.existsByMemberNickName(memberNickName)){
            throw new ExistMemberNickNameException();
        }
    }
}
