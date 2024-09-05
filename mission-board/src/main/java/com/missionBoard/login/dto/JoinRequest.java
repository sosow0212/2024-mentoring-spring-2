package com.missionBoard.login.dto;


import com.missionBoard.login.domain.Member;

public record JoinRequest(
        String loginId,
        String password,
        String email,
        String username
) {
    public Member toEntity() {
        return Member.builder()
                .loginId(this.loginId())
                .password(this.password())
                .email(this.email())
                .username(this.username())
                .build();
    }
}
