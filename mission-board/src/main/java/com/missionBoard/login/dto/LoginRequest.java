package com.missionBoard.login.dto;

public record LoginRequest(
        String loginId,
        String password) {
}
