package com.board.member.mapper;

import com.board.member.controller.dto.CreateRequest;
import com.board.member.controller.dto.CreateResponse;
import com.board.member.domain.Member;

public class MemberMapper {

    public static Member toMember(CreateRequest createRequest){
        return new Member(createRequest.memName(), createRequest.memNickName(), createRequest.memLoginId(), createRequest.memPassword());
    }

    public static CreateResponse toCreateResponse(Member member){
        return new CreateResponse(member.getId(), member.getMemName(), member.getMemNickName(), member.getMemLoginId(), member.getMemPassword());
    }
}
