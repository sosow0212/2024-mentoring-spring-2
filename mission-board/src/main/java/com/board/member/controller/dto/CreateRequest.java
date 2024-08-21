package com.board.member.controller.dto;

public record CreateRequest(
        String memberName,
        String memberNickName,
        String memberLoginId,
        String memberPassword
) {
}
