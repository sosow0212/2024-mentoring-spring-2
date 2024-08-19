package com.board.login.controller.dto;

public record LoginRequest(
        String memLoginId,
        String memPassword
) {
}
