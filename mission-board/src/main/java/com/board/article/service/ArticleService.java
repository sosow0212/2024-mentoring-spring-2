package com.board.article.service;

import com.board.article.controller.dto.ArticleRequest;
import com.board.article.domain.Article;
import com.board.article.repository.ArticleRepository;
import com.board.article.service.exception.ArticleRightException;
import com.board.article.service.exception.ExistArticleException;
import com.board.member.domain.Member;
import com.board.member.service.event.MemberFindEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleService {

    private final ApplicationEventPublisher publisher;
    private final ArticleRepository articleRepository;

    public Article createArticle(Long memberId, ArticleRequest articleRequest) {
        Member member = foundMemberByEvent(memberId);
        Article article = new Article(member, articleRequest.title(), articleRequest.content());
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
        Member member = foundMemberByEvent(memberId);
        Article article = checkRightAboutArticle(articleId, member);
        article.updateArticle(articleRequest.title(), articleRequest.content());
        return article;
    }

    public Article deleteArticle(Long articleId, Long memberId) {
        Member member = foundMemberByEvent(memberId);
        Article article = checkRightAboutArticle(articleId, member);
        articleRepository.delete(article);
        return article;
    }

    public List<Article> findMemberArticles(Long memberId) {
        return articleRepository.findByMemberId(memberId);
    }

    private Member foundMemberByEvent(Long memberId) {
        MemberFindEvent memberFindEvent = new MemberFindEvent(memberId);
        publisher.publishEvent(memberFindEvent);
        return memberFindEvent.getFuture()
                .join();
    }

    private Article checkRightAboutArticle(Long articleId, Member member) {
        Article article = findArticle(articleId);
        if (!member.isSameMember(article.getMember().getId())) {
            throw new ArticleRightException();
        }
        return article;
    }
}
