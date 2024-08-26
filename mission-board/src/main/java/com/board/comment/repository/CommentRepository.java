package com.board.comment.repository;

import com.board.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByArticleId(Long articleId);

    List<Comment> findByMemberId(Long memberId);
}
