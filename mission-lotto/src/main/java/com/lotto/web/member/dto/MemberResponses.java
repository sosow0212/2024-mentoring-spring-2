package com.lotto.web.member.dto;

import com.lotto.web.member.dto.exception.NotFoundMemberException;

import java.util.List;

public record MemberResponses(List<MemberResponse> memberResponses) {

    public static MemberResponses form(List<MemberResponse> memberResponses) {
        validateMemberExist(memberResponses);
        return new MemberResponses(memberResponses);
    }

    private static void validateMemberExist(List<MemberResponse> memberResponses) {
        if (memberResponses.isEmpty()) {
            throw new NotFoundMemberException();
        }
    }
}
