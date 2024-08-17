package com.board.Member.controller.dto;

public record CreateRequest(
        String memName,
        String memNickName,
        String memId,
        String memPassword
) {
}
