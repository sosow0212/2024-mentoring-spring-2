package com.board.Member.controller.dto;

public record CreateResponse(
        Long id,
        String memName,
        String memNickName,
        String memId,
        String memPassword
) {
}
