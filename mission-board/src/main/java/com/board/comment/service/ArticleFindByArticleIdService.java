package com.board.comment.service;

import com.board.article.domain.Article;
import com.board.article.repository.ArticleRepository;
import com.board.article.service.ArticleFindByArticleId;
import com.board.global.exception.exceptions.CustomErrorCode;
import com.board.global.exception.exceptions.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ArticleFindByArticleIdService implements ArticleFindByArticleId {

    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    @Override
    public Article findArticle(Long articleId) {
        return articleRepository.findById(articleId).orElseThrow(() -> new CustomException(CustomErrorCode.NOT_EXIST_ARTICLE));
    }
}
