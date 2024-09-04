package com.board.comment.controller.dto;

public record CommentResponse(
        Long id,
        Long articleId,
        Long memberId,
        String content
) {
}
