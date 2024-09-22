package com.board.article.controller;

import com.board.article.controller.dto.ArticleRequest;
import com.board.article.domain.Article;
import com.board.article.service.ArticleService;
import com.board.global.resolver.LoginArgumentResolver;
import com.fasterxml.jackson.databind.ObjectMapper;
import groovy.util.logging.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ArticleController.class)
@Slf4j
public class ArticleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ArticleService articleService;

    @MockBean
    private LoginArgumentResolver loginArgumentResolver;

    private Long memberId;
    private String token;

    @BeforeEach
    void setUp() {
        token = "Bearer jwt";
        memberId = 1L;
    }


    @DisplayName("게시글 생성 테스트.")
    @Test
    void create_article() throws Exception {
        //given
        ArticleRequest articleRequest = new ArticleRequest("제목", "내용");
        Article createdArticle = new Article(1L, 1L, "제목", "내용");
        when(loginArgumentResolver.supportsParameter(any())).thenReturn(true);
        when(loginArgumentResolver.resolveArgument(any(), any(), any(), any())).thenReturn(memberId);
        when(articleService.createArticle(memberId, articleRequest)).thenReturn(createdArticle);

        //when&then
        mockMvc.perform(post("/api/articles")
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(articleRequest)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/api/articles/1"))
                .andDo(print());
    }

    @DisplayName("모든 게시글 조회 테스트.")
    @Test
    void show_all_article() throws Exception {
        //given
        List<Article> articles = List.of(new Article(1L, 1L, "제목", "내용"));
        when(articleService.findAllArticles()).thenReturn(articles);

        //when&then
        mockMvc.perform(get("/api/articles")
                        .header("Authorization", token))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @DisplayName("단일 게시글 조회 테스트")
    @Test
    void show_single_article() throws Exception {
        //given
        Article article = new Article(1L, 1L, "제목", "내용");
        Long articleId = 1L;
        when(articleService.findArticle(articleId)).thenReturn(article);

        //when&then
        mockMvc.perform(get("/api/articles/{articleId}", article.getId())
                        .header("Authorization", token))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @DisplayName("특정 유저의 게시글 조회 테스트")
    @Test
    void show_member_article() throws Exception {
        //given
        List<Article> memberArticles = List.of(new Article(1L, 1L, "제목", "내용"));
        when(loginArgumentResolver.supportsParameter(any())).thenReturn(true);
        when(loginArgumentResolver.resolveArgument(any(), any(), any(), any())).thenReturn(memberId);
        when(articleService.findMemberArticles(memberId)).thenReturn(memberArticles);

        //when&then
        mockMvc.perform(get("/api/members/me/articles")
                        .header("Authorization", token))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @DisplayName("게시글 수정 테스트.")
    @Test
    void update_article() throws Exception {
        //given
        ArticleRequest articleRequest = new ArticleRequest("제목 수정", "내용 수정");
        Article updatedArticle = new Article(1L, 1L, "제목 수정", "내용 수정");
        Long articleId = 1L;
        when(loginArgumentResolver.supportsParameter(any())).thenReturn(true);
        when(loginArgumentResolver.resolveArgument(any(), any(), any(), any())).thenReturn(memberId);
        when(articleService.updateArticle(articleId, memberId, articleRequest)).thenReturn(updatedArticle);

        //when&then
        mockMvc.perform(patch("/api/articles/{articleId}", articleId)
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(articleRequest)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @DisplayName("게시글 삭제 테스트.")
    @Test
    void delete_article() throws Exception {
        //given
        Article deletedArticle = new Article(1L, 1L, "제목", "내용");
        when(loginArgumentResolver.supportsParameter(any())).thenReturn(true);
        when(loginArgumentResolver.resolveArgument(any(), any(), any(), any())).thenReturn(memberId);
        when(articleService.deleteArticle(memberId, deletedArticle.getId())).thenReturn(deletedArticle);

        //when&then
        mockMvc.perform(delete("/api/articles/{articleId}", deletedArticle.getId())
                        .header("Authorization", token))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
