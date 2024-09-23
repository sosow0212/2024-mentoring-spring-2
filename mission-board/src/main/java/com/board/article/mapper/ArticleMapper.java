package com.board.article.mapper;

import com.board.article.controller.dto.ArticleResponse;
import com.board.article.controller.dto.ArticleResponses;
import com.board.article.domain.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleMapper {

    public static ArticleResponse toArticleResponse(Article article) {
        return new ArticleResponse(article.getId(), article.getMemberId(), article.getTitle(), article.getContent());
    }

    public static ArticleResponses toArticleResponses(List<Article> articles) {
        return new ArticleResponses(makeArticleResponses(articles));
    }

    private static List<ArticleResponse> makeArticleResponses(List<Article> articles) {
        List<ArticleResponse> articleResponses = new ArrayList<>();
        for (Article article : articles) {
            articleResponses.add(toArticleResponse(article));
        }
        return articleResponses;
    }
}
