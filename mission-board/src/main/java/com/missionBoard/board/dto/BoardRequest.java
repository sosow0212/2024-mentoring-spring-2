package com.missionBoard.board.dto;

import com.missionBoard.board.domain.Board;
import com.missionBoard.login.domain.Member;

public record BoardRequest(
        String title,
        String content,
        String loginId
) {
    public Board toEntity(Member member) {
        return Board.builder()
                .title(this.title())
                .content(this.content())
                .member(member)
                .build();
    }
}
