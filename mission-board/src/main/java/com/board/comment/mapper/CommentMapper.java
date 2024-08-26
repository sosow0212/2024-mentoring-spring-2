package com.board.comment.mapper;

import com.board.article.domain.Article;
import com.board.comment.controller.dto.CommentRequest;
import com.board.comment.controller.dto.CommentResponse;
import com.board.comment.controller.dto.CommentResponses;
import com.board.comment.domain.Comment;
import com.board.member.domain.Member;

import java.util.ArrayList;
import java.util.List;

public class CommentMapper {

    public static Comment toComment(Member member, Article article, CommentRequest commentRequest) {
        return new Comment(member, article, commentRequest.content());
    }

    public static CommentResponse toCommentResponse(Comment comment) {
        return new CommentResponse(comment.getArticle().getId(), comment.getMember().getMemberNickName(), comment.getContent());
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
