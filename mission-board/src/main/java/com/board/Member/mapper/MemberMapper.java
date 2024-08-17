package com.board.Member.mapper;

import com.board.Member.controller.dto.CreateRequest;
import com.board.Member.controller.dto.CreateResponse;
import com.board.Member.domain.Member;

public class MemberMapper {

    public static Member toMember(CreateRequest createRequest){
        return new Member(createRequest.memName(), createRequest.memNickName(), createRequest.memId(), createRequest.memPassword());
    }

    public static CreateResponse toCreateResponse(Member member){
        return new CreateResponse(member.getId(), member.getMemName(), member.getMemNickName(), member.getMemId(), member.getMemPassword());
    }
}
