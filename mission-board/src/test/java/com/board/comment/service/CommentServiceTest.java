package com.board.comment.service;

import com.board.comment.controller.dto.CommentRequest;
import com.board.comment.domain.Comment;
import com.board.comment.repository.CommentRepository;
import com.board.comment.service.exception.CommentRightException;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentService commentService;

    private CommentRequest commentRequest;
    private Comment comment;

    @BeforeEach
    void setup() {
        commentRequest = new CommentRequest("안녕");
        comment = new Comment(1L, 1L, "aa");
    }

    @Test
    @DisplayName("댓글 생성하기.")
    void create_comment() {
        // given
        when(commentRepository.save(any(Comment.class))).thenReturn(comment);

        //when
        Comment result = commentService.createComment(commentRequest, 1L, 1L);

        //then
        assertEquals("안녕", result.getContent());
        verify(commentRepository).save(any(Comment.class));
    }

    @Test
    @DisplayName("해당 게시글의 모든 댓글 보여주기.")
    void show_article_comments() {
        // given
        when(commentRepository.findByArticleId(anyLong())).thenReturn(List.of(comment));

        //when
        List<Comment> result = commentService.showAllComments(1L);

        //then
        assertEquals(1, result.size());
        verify(commentRepository).findByArticleId(anyLong());
    }

    @Test
    @DisplayName("해당 유저의 모든 댓글 보여주기.")
    void show_member_comments() {
        //given
        when(commentRepository.findByMemberId(anyLong())).thenReturn(List.of(comment));

        //when
        List<Comment> result = commentService.showMemberComments(1L);

        //then
        assertEquals(1, result.size());
        verify(commentRepository).findByMemberId(anyLong());
    }

    @Test
    @DisplayName("댓글 업데이트하기.")
    void update_comment() {
        //given
        when(commentRepository.findById(anyLong())).thenReturn(Optional.ofNullable(comment));

        //when
        Comment updatedComment = commentService.updateComment(commentRequest, 1L, 1L);

        //then
        assertEquals(commentRequest.content(), updatedComment.getContent());
    }

    @Test
    @DisplayName("권한이 없는 댓글 업데이트 하기.")
    void update_comment_throw_exception() {
        //given
        when(commentRepository.findById(anyLong())).thenReturn(Optional.ofNullable(comment));

        //when&then
        assertThrows(CommentRightException.class, () -> commentService.updateComment(commentRequest, 2L, 1L));
    }

    @Test
    @DisplayName("댓글 삭제하기.")
    void delete_comment() {
        //given
        when(commentRepository.findById(anyLong())).thenReturn(Optional.ofNullable(comment));

        //when
        commentService.deleteComment(1L, 1L);

        //then
        verify(commentRepository).delete(any(Comment.class));
    }

    @Test
    @DisplayName("권한이 없는 댓글 삭제하기.")
    void delete_comment_throw_exception() {
        //given
        when(commentRepository.findById(anyLong())).thenReturn(Optional.ofNullable(comment));

        //when&then
        assertThrows(CommentRightException.class, () -> commentService.deleteComment(2L, 1L));
    }
}
