package com.board.comment.controller;

import com.board.comment.controller.dto.CommentRequest;
import com.board.comment.controller.dto.CommentResponse;
import com.board.comment.controller.dto.CommentResponses;
import com.board.comment.mapper.CommentMapper;
import com.board.comment.service.CommentService;
import com.board.global.annotation.Login;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/articles/{articleId}/comments")
    public ResponseEntity<CommentResponse> createComment(@RequestBody CommentRequest commentRequest, @PathVariable Long articleId, @Login Long memberId) {
        CommentResponse commentResponse = CommentMapper.toCommentResponse(commentService.createComment(commentRequest, memberId, articleId));
        URI location = URI.create("/api/articles/" + commentResponse.articleId());
        log.info("{}번 게시물, {}님 댓글 작성 완료. [내용]: {}", commentResponse.articleId(), commentResponse.memberNickName(), commentResponse.content());
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/articles/{articleId}/comments")
    public ResponseEntity<CommentResponses> showAllComments(@PathVariable Long articleId) {
        CommentResponses commentResponses = CommentMapper.toCommentResponses(commentService.showAllComments(articleId));
        log.info("게시물에 대한 모든 댓글 조회 완료.");
        return ResponseEntity.ok(commentResponses);
    }

    @PatchMapping("/comments/{commentId}")
    public ResponseEntity<CommentResponse> updateComment(@PathVariable Long commentId, @Login Long memberId, @RequestBody CommentRequest commentRequest) {
        CommentResponse commentResponse = CommentMapper.toCommentResponse(commentService.updateComment(commentRequest, memberId, commentId));
        log.info("{}님 댓글 수정 완료. [내용]: {}", commentResponse.memberNickName(), commentResponse.content());
        return ResponseEntity.ok(commentResponse);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<CommentResponse> deleteComment(@PathVariable Long commentId, @Login Long memberId) {
        CommentResponse commentResponse = CommentMapper.toCommentResponse(commentService.deleteComment(memberId, commentId));
        log.info("{}님 댓글 삭제 완료", commentResponse.memberNickName());
        return ResponseEntity.ok(commentResponse);
    }

    @GetMapping("/members/me/comments")
    public ResponseEntity<CommentResponses> showMemberComments(@Login Long memberId) {
        CommentResponses commentResponses = CommentMapper.toCommentResponses(commentService.showMemberComments(memberId));
        log.info("{}님 모든 댓글을 불러왔습니다.", commentResponses);
        return ResponseEntity.ok(commentResponses);
    }
}
