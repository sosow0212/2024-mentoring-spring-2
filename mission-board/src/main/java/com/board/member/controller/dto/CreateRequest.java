package com.board.member.controller.dto;

public record CreateRequest(
        String memName,
        String memNickName,
        String memLoginId,
        String memPassword
) {
}
