package com.board.comment.mapper;

import com.board.comment.controller.dto.CommentRequest;
import com.board.comment.controller.dto.CommentResponse;
import com.board.comment.controller.dto.CommentResponses;
import com.board.comment.domain.Comment;

import java.util.ArrayList;
import java.util.List;

public class CommentMapper {

    public static Comment toComment(Long memberId, Long articleId, CommentRequest commentRequest) {
        return new Comment(memberId, articleId, commentRequest.content());
    }

    public static CommentResponse toCommentResponse(Comment comment) {
        return new CommentResponse(comment.getId(), comment.getArticleId(), comment.getMemberId(), comment.getContent());
    }

    public static CommentResponses toCommentResponses(List<Comment> comments) {
        return new CommentResponses(makeArticleResponses(comments));
    }

    private static List<CommentResponse> makeArticleResponses(List<Comment> comments) {
        List<CommentResponse> commentResponses = new ArrayList<>();
        for (Comment comment : comments) {
            commentResponses.add(toCommentResponse(comment));
        }
        return commentResponses;
    }
}
