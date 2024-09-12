package com.board.article.service;

import com.board.article.controller.dto.ArticleRequest;
import com.board.article.domain.Article;
import com.board.article.repository.ArticleRepository;
import com.board.article.service.exception.ArticleRightException;
import com.board.article.service.exception.ExistArticleException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class ArticleServiceTest {

    @Mock
    private ArticleRepository articleRepository;

    @InjectMocks
    private ArticleService articleService;

    private ArticleRequest articleRequest;
    private Article article;

    @BeforeEach
    void setUp() {
        article = new Article(1L, "제목1", "내용1");
        articleRequest = new ArticleRequest("제목", "내용");
    }

    @Test
    @DisplayName("글 생성하기.")
    void create_article() {
        //given
        when(articleRepository.save(any(Article.class))).thenReturn(article);

        //when
        Article result = articleService.createArticle(1L, articleRequest);

        //then
        assertEquals("제목", result.getTitle());
        verify(articleRepository).save(any(Article.class));
    }

    @Test
    @DisplayName("모든 글 불러오기.")
    void show_all_article() {
        //given
        when(articleRepository.findAll()).thenReturn(List.of(article));

        //when
        List<Article> result = articleService.findAllArticles();

        //then
        assertEquals(1, result.size());
        verify(articleRepository).findAll();
    }

    @Test
    @DisplayName("해당 유저의 모든 글 불러오기.")
    void show_member_article() {
        //given
        when(articleRepository.findByMemberId(1L)).thenReturn(List.of(article));

        //when
        List<Article> result = articleService.findMemberArticles(1L);

        //then
        assertEquals(1, result.size());
        verify(articleRepository).findByMemberId(1L);
    }

    @Test
    @DisplayName("글 불러오기.")
    void show_article() {
        //given
        when(articleRepository.findById(1L)).thenReturn(Optional.of(article));

        //when
        Article result = articleService.findArticle(1L);

        //then
        assertEquals("제목1", result.getTitle());
        verify(articleRepository).findById(1L);
    }

    @Test
    @DisplayName("존재하지 않는 글 불러오기.")
    void show_article_throw_exception() {
        //given
        when(articleRepository.findById(1L)).thenReturn(Optional.empty());

        //when&then
        assertThrows(ExistArticleException.class, () -> articleService.findArticle(1L));
    }

    @Test
    @DisplayName("글 업데이트하기.")
    void update_article() {
        //given
        when(articleRepository.findById(1L)).thenReturn(Optional.of(article));

        //when
        Article updatedArticle = articleService.updateArticle(1L, 1L, articleRequest);

        //then
        assertEquals("제목", updatedArticle.getTitle());
        verify(articleRepository).findById(1L);
    }

    @Test
    @DisplayName("권한이 없는 글 업데이트하기.")
    void update_article_throw_exception() {
        //given
        when(articleRepository.findById(anyLong())).thenReturn(Optional.of(article));

        //when&then
        assertThrows(ArticleRightException.class, () -> articleService.updateArticle(1L, 2L, articleRequest));
    }

    @Test
    @DisplayName("글 삭제하기.")
    void delete_article() {
        //given
        when(articleRepository.findById(anyLong())).thenReturn(Optional.of(article));

        //when
        Article deletedArticle = articleService.deleteArticle(1L, 1L);

        //then
        assertEquals("제목1", deletedArticle.getTitle());
        verify(articleRepository).delete(any(Article.class));
    }
}
