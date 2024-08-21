package com.board.login.controller.dto;

public record LoginRequest(
        String memberLoginId,
        String memberPassword
) {
}
