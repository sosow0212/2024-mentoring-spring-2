package com.board.member.controller.dto;

public record CreateResponse(
        Long id,
        String memName,
        String memNickName,
        String memLoginId,
        String memPassword
) {
}
