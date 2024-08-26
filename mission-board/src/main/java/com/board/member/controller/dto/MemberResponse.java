package com.board.member.controller.dto;

public record MemberResponse(
        Long id,
        String memberName,
        String memberNickName,
        String memberLoginId,
        String memberPassword
) {
}
