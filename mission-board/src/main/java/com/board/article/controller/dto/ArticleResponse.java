package com.board.article.controller.dto;

public record ArticleResponse(
        Long id,
        Long memberId,
        String title,
        String content
) {
}
