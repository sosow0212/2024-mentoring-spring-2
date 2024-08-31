package com.board.comment.service;

import com.board.article.domain.Article;
import com.board.article.service.event.ArticleFindEvent;
import com.board.comment.controller.dto.CommentRequest;
import com.board.comment.domain.Comment;
import com.board.comment.mapper.CommentMapper;
import com.board.comment.repository.CommentRepository;
import com.board.comment.service.exception.CommentRightException;
import com.board.comment.service.exception.ExistCommentException;
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
public class CommentService {

    private final CommentRepository commentRepository;
    private final ApplicationEventPublisher publisher;

    public Comment createComment(CommentRequest commentRequest, Long memberId, Long articleId) {
        Article article = findArticleByEvent(articleId);
        Member member = findMemberByEvent(memberId);
        Comment comment = CommentMapper.toComment(member, article, commentRequest);
        commentRepository.save(comment);
        return comment;
    }

    @Transactional(readOnly = true)
    public List<Comment> showAllComments(Long articleId) {
        Article article = findArticleByEvent(articleId);
        return commentRepository.findByArticleId(article.getId());
    }

    public Comment updateComment(CommentRequest commentRequest, Long memberId, Long commentId) {
        Member member = findMemberByEvent(memberId);
        Comment comment = checkRightAboutComment(commentId, member);
        comment.updateComment(commentRequest.content());
        return comment;
    }

    public Comment deleteComment(Long memberId, Long commentId) {
        Member member = findMemberByEvent(memberId);
        Comment comment = checkRightAboutComment(commentId, member);
        commentRepository.delete(comment);
        return comment;
    }

    public List<Comment> showMemberComments(Long memberId) {
        return commentRepository.findByMemberId(memberId);
    }

    private Article findArticleByEvent(Long articleId){
        ArticleFindEvent articleFindEvent = new ArticleFindEvent(articleId);
        publisher.publishEvent(articleFindEvent);
        return articleFindEvent.getFuture()
                .join();
    }
    private Member findMemberByEvent(Long memberId) {
        MemberFindEvent memberFindEvent = new MemberFindEvent(memberId);
        publisher.publishEvent(memberFindEvent);
        return memberFindEvent.getFuture()
                .join();
    }

    private Comment checkRightAboutComment(Long commentId, Member member) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(ExistCommentException::new);
        if (!member.isSameMember(comment.getMember().getId())) {
            throw new CommentRightException();
        }
        return comment;
    }
}
