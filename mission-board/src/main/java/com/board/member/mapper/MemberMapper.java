package com.board.member.mapper;

import com.board.login.controller.dto.LoginResponse;
import com.board.login.controller.dto.LogoutResponse;
import com.board.member.controller.dto.CreateRequest;
import com.board.member.controller.dto.CreateResponse;
import com.board.member.domain.Member;

public class MemberMapper {

    public static Member toMember(CreateRequest createRequest) {
        return new Member(createRequest.memberName(), createRequest.memberNickName(), createRequest.memberLoginId(), createRequest.memberPassword());
    }

    public static CreateResponse toCreateResponse(Member member) {
        return new CreateResponse(member.getId(), member.getMemberName(), member.getMemberNickName(), member.getMemberLoginId(), member.getMemberPassword());
    }

    public static LoginResponse toLoginResponse(Member member) {
        return new LoginResponse(member.getId(), member.getMemberNickName());
    }

    public static LogoutResponse toLogoutResponse(Member member) {
        return new LogoutResponse(member.getId(), member.getMemberNickName());
    }
}
