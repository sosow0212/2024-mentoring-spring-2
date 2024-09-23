package com.board.comment.controller;

import com.board.comment.controller.dto.CommentRequest;
import com.board.comment.domain.Comment;
import com.board.comment.service.CommentService;
import com.board.global.resolver.LoginArgumentResolver;
import com.fasterxml.jackson.databind.ObjectMapper;
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

@WebMvcTest(CommentController.class)
public class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CommentService commentService;

    @MockBean
    private LoginArgumentResolver loginArgumentResolver;

    private Long memberId;
    private Long articleId;
    private String token;

    @BeforeEach
    void setUp() {
        memberId = 1L;
        articleId = 1L;
        token = "Bearer jwt";
    }

    @DisplayName("댓글 작성 테스트.")
    @Test
    void create_comment() throws Exception {
        //given
        CommentRequest commentRequest = new CommentRequest("댓글");
        Comment createdComment = new Comment(1L, 1L, "댓글");
        when(loginArgumentResolver.supportsParameter(any())).thenReturn(true);
        when(loginArgumentResolver.resolveArgument(any(), any(), any(), any())).thenReturn(memberId);
        when(commentService.createComment(commentRequest, memberId, articleId)).thenReturn(createdComment);

        //when&then
        mockMvc.perform(post("/api/articles/{articleId}/comments", articleId)
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(commentRequest)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/api/articles/" + articleId))
                .andDo(print());
    }

    @DisplayName("특정 게시물에 대한 댓글 조회 테스트.")
    @Test
    void show_article_comment() throws Exception {
        //given
        List<Comment> articleComments = List.of(new Comment(1L, 1L, "댓글"));
        when(commentService.showArticleComments(articleId)).thenReturn(articleComments);

        //when&then
        mockMvc.perform(get("/api/articles/{articleId}/comments", articleId)
                        .header("Authorization", token))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @DisplayName("특정 유저의 댓글 조회 테스트.")
    @Test
    void show_member_comment() throws Exception {
        //given
        List<Comment> memberComments = List.of(new Comment(1L, 1L, "댓글"));
        when(loginArgumentResolver.supportsParameter(any())).thenReturn(true);
        when(loginArgumentResolver.resolveArgument(any(), any(), any(), any())).thenReturn(memberId);
        when(commentService.showMemberComments(memberId)).thenReturn(memberComments);

        //when&then
        mockMvc.perform(get("/api/members/me/comments")
                        .header("Authorization", token))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @DisplayName("댓글 수정 테스트.")
    @Test
    void update_comment() throws Exception {
        //given
        CommentRequest commentRequest = new CommentRequest("댓글 수정");
        Comment updatedComment = new Comment(1L, 1L, 1L, "댓글 수정");
        when(loginArgumentResolver.supportsParameter(any())).thenReturn(true);
        when(loginArgumentResolver.resolveArgument(any(), any(), any(), any())).thenReturn(memberId);
        when(commentService.updateComment(commentRequest, memberId, articleId)).thenReturn(updatedComment);

        //when&then
        mockMvc.perform(patch("/api/comments/{commentId}", updatedComment.getId())
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(commentRequest)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @DisplayName("댓글 삭제 테스트")
    @Test
    void delete_comment() throws Exception {
        //given
        Comment deletedComment = new Comment(1L, 1L, 1L, "댓글");
        when(loginArgumentResolver.supportsParameter(any())).thenReturn(true);
        when(loginArgumentResolver.resolveArgument(any(), any(), any(), any())).thenReturn(memberId);
        when(commentService.deleteComment(memberId, articleId)).thenReturn(deletedComment);

        //when&then
        mockMvc.perform(delete("/api/comments/{commentId}", deletedComment.getId())
                        .header("Authorization", token))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
