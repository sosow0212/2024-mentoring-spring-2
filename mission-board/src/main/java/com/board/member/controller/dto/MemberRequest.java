package com.board.member.controller.dto;

public record MemberRequest(
        String memberName,
        String memberNickName,
        String memberLoginId,
        String memberPassword
) {
}
