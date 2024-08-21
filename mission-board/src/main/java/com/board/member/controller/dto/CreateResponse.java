package com.board.member.controller.dto;

public record CreateResponse(
        Long id,
        String memberName,
        String memberNickName,
        String memberLoginId,
        String memberPassword
) {
}
