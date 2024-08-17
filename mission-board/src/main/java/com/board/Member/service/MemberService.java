package com.board.Member.service;

import com.board.Member.controller.dto.CreateRequest;
import com.board.Member.domain.Member;
import com.board.Member.mapper.MemberMapper;
import com.board.Member.repository.MemberRepository;
import com.board.Member.service.exception.ExistMemberIdException;
import com.board.Member.service.exception.ExistMemberNickNameException;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member signUp(CreateRequest createRequest){
        Member member = MemberMapper.toMember(createRequest);
        checkDuplicateMemId(member.getMemId());
        checkDuplicateMemNickName(member.getMemNickName());
        memberRepository.save(member);
        return member;
    }

    private void checkDuplicateMemId(String memId){
        if(memberRepository.existsByMemId(memId)){
            throw new ExistMemberIdException();
        }
    }

    private void checkDuplicateMemNickName(String memNickName){
        if(memberRepository.existsByMemNickName(memNickName)){
            throw new ExistMemberNickNameException();
        }
    }
}
