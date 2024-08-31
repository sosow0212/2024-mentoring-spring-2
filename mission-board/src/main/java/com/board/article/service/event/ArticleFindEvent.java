package com.board.article.service.event;

import com.board.article.domain.Article;
import lombok.Getter;

import java.util.concurrent.CompletableFuture;

@Getter
public class ArticleFindEvent {

    private final Long articleId;
    private final CompletableFuture<Article> future = new CompletableFuture<>();

    public ArticleFindEvent(Long articleId) {
        this.articleId = articleId;
    }

    public CompletableFuture<Article> getFuture() {
        return future;
    }
}
