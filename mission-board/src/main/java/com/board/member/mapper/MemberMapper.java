package com.board.member.mapper;

import com.board.login.controller.dto.LoginResponse;
import com.board.login.controller.dto.LogoutResponse;
import com.board.member.controller.dto.MemberRequest;
import com.board.member.controller.dto.MemberResponse;
import com.board.member.domain.Member;

public class MemberMapper {

    public static Member toMember(MemberRequest memberRequest) {
        return new Member(memberRequest.memberName(), memberRequest.memberNickName(), memberRequest.memberLoginId(), memberRequest.memberPassword());
    }

    public static MemberResponse toMemberResponse(Member member) {
        return new MemberResponse(member.getId(), member.getMemberName(), member.getMemberNickName(), member.getMemberLoginId(), member.getMemberPassword());
    }

    public static LoginResponse toLoginResponse(Member member) {
        return new LoginResponse(member.getId(), member.getMemberNickName());
    }

    public static LogoutResponse toLogoutResponse(Member member) {
        return new LogoutResponse(member.getId(), member.getMemberNickName());
    }
}
