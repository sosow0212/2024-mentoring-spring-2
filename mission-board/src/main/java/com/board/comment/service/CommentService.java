package com.board.comment.service;

import com.board.comment.controller.dto.CommentRequest;
import com.board.comment.domain.Comment;
import com.board.comment.mapper.CommentMapper;
import com.board.comment.repository.CommentRepository;
import com.board.comment.service.exception.CommentRightException;
import com.board.comment.service.exception.ExistCommentException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public Comment createComment(CommentRequest commentRequest, Long memberId, Long articleId) {
        Comment comment = CommentMapper.toComment(memberId, articleId, commentRequest);
        commentRepository.save(comment);
        return comment;
    }

    @Transactional(readOnly = true)
    public List<Comment> showAllComments(Long articleId) {
        return commentRepository.findByArticleId(articleId);
    }

    public Comment updateComment(CommentRequest commentRequest, Long memberId, Long commentId) {
        Comment comment = checkRightAboutComment(commentId, memberId);
        comment.updateComment(commentRequest.content());
        return comment;
    }

    public Comment deleteComment(Long memberId, Long commentId) {
        Comment comment = checkRightAboutComment(commentId, memberId);
        commentRepository.delete(comment);
        return comment;
    }

    public List<Comment> showMemberComments(Long memberId) {
        return commentRepository.findByMemberId(memberId);
    }

    private Comment checkRightAboutComment(Long commentId, Long memberId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(ExistCommentException::new);
        if (!Objects.equals(comment.getMemberId(), memberId)) {
            throw new CommentRightException();
        }
        return comment;
    }
}
