package com.board.article.service;

import com.board.article.controller.dto.ArticleRequest;
import com.board.article.domain.Article;
import com.board.article.repository.ArticleRepository;
import com.board.article.service.exception.ArticleRightException;
import com.board.article.service.exception.ExistArticleException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public Article createArticle(Long memberId, ArticleRequest articleRequest) {
        Article article = new Article(memberId, articleRequest.title(), articleRequest.content());
        articleRepository.save(article);
        return article;
    }

    @Transactional(readOnly = true)
    public List<Article> findAllArticles() {
        return articleRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Article findArticle(Long articleId) {
        return articleRepository.findById(articleId)
                .orElseThrow(ExistArticleException::new);
    }

    public Article updateArticle(Long articleId, Long memberId, ArticleRequest articleRequest) {
        Article article = checkRightAboutArticle(articleId, memberId);
        article.updateArticle(articleRequest.title(), articleRequest.content());
        return article;
    }

    public Article deleteArticle(Long articleId, Long memberId) {
        Article article = checkRightAboutArticle(articleId, memberId);
        articleRepository.delete(article);
        return article;
    }

    public List<Article> findMemberArticles(Long memberId) {
        return articleRepository.findByMemberId(memberId);
    }

    private Article checkRightAboutArticle(Long articleId, Long memberId) {
        Article article = findArticle(articleId);
        if (!Objects.equals(article.getMemberId(), memberId)) {
            throw new ArticleRightException();
        }
        return article;
    }
}
