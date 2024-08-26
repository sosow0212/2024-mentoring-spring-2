package com.board.comment.controller.dto;

public record CommentResponse(
        Long articleId,
        String memberNickName,
        String content
) {
}
