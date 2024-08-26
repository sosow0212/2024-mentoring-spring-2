package com.board.article.controller.dto;

public record ArticleResponse(
        Long id,
        String memberNickName,
        String title,
        String content
) {
}
