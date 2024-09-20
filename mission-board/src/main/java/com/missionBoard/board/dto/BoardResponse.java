package com.missionBoard.board.dto;

import com.missionBoard.board.domain.Board;

public record BoardResponse(
        Long boardId,
        String title,
        String content,
        String loginId
) {
    public BoardResponse(Long boardId, String title, String content, String loginId) {
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.loginId = loginId;
    }

    public static BoardResponse from(Board board) {
        return new BoardResponse(
                board.getId(),
                board.getTitle(),
                board.getContent(),
                board.getMember().getLoginId()
        );
    }
}
