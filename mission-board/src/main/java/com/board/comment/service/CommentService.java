package com.board.comment.service;

import com.board.article.domain.Article;
import com.board.comment.controller.dto.CommentRequest;
import com.board.comment.domain.Comment;
import com.board.comment.mapper.CommentMapper;
import com.board.comment.repository.CommentRepository;
import com.board.comment.service.exception.CommentRightException;
import com.board.comment.service.exception.ExistCommentException;
import com.board.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ArticleFindByArticleIdService articleFindService;

    public Comment createComment(CommentRequest commentRequest, Member member, Long articleId) {
        Article article = articleFindService.findArticle(articleId);
        Comment comment = CommentMapper.toComment(member, article, commentRequest);
        commentRepository.save(comment);
        return comment;
    }

    @Transactional(readOnly = true)
    public List<Comment> showAllComments(Long articleId) {
        Article article = articleFindService.findArticle(articleId);
        List<Comment> comments = commentRepository.findByArticleId(article.getId());
        checkCommentExist(comments);
        return comments;
    }

    public Comment updateComment(CommentRequest commentRequest, Member member, Long commentId) {
        Comment comment = checkRightAboutComment(commentId, member);
        comment.updateComment(commentRequest.content());
        return comment;
    }

    public Comment deleteComment(Member member, Long commentId) {
        Comment comment = checkRightAboutComment(commentId, member);
        commentRepository.delete(comment);
        return comment;
    }

    public List<Comment> showMemberComments(Member member) {
        List<Comment> comments = commentRepository.findByMemberId(member.getId());
        checkCommentExist(comments);
        return comments;
    }

    private void checkCommentExist(List<Comment> comments) {
        if (comments.isEmpty()) {
            throw new ExistCommentException();
        }
    }

    private Comment checkRightAboutComment(Long commentId, Member member) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(ExistCommentException::new);
        if (!member.isMemberComment(comment.getMember().getId())) {
            throw new CommentRightException();
        }
        return comment;
    }
}
