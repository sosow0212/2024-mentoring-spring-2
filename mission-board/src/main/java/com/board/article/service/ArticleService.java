package com.board.article.service;

import com.board.article.controller.dto.ArticleRequest;
import com.board.article.domain.Article;
import com.board.article.repository.ArticleRepository;
import com.board.article.service.exception.ArticleRightException;
import com.board.article.service.exception.ExistArticleException;
import com.board.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public Article createArticle(Member member, ArticleRequest articleRequest) {
        Article article = new Article(member, articleRequest.title(), articleRequest.content());
        articleRepository.save(article);
        return article;
    }

    @Transactional(readOnly = true)
    public List<Article> findAllArticles() {
        List<Article> articles = articleRepository.findAll();
        checkArticleExist(articles);
        return articles;
    }

    @Transactional(readOnly = true)
    public Article findArticle(Long articleId) {
        return articleRepository.findById(articleId).orElseThrow(ExistArticleException::new);
    }

    public Article updateArticle(Long articleId, Member member, ArticleRequest articleRequest) {
        Article article = checkRightAboutArticle(articleId, member);
        article.updateTitle(articleRequest.title());
        article.updateContent(articleRequest.content());
        return article;
    }

    public Article deleteArticle(Long articleId, Member member) {
        Article article = checkRightAboutArticle(articleId, member);
        articleRepository.delete(article);
        return article;
    }

    public List<Article> findMemberArticles(Member member) {
        List<Article> articles = articleRepository.findByMemberId(member.getId());
        checkArticleExist(articles);
        return articles;
    }

    private Article checkRightAboutArticle(Long articleId, Member member) {
        Article article = findArticle(articleId);
        if (!member.isMemberArticle(article.getMember().getId())) {
            throw new ArticleRightException();
        }
        return article;
    }

    private void checkArticleExist(List<Article> articles) {
        if (articles.isEmpty()) {
            throw new ExistArticleException();
        }
    }
}
