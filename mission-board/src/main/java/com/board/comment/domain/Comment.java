package com.board.comment.domain;

import com.board.article.domain.Article;
import com.board.member.domain.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "articleId")
    private Article article;
    @Column
    private String content;

    public Comment(Member member, Article article, String content) {
        this.member = member;
        this.article = article;
        this.content = content;
    }

    public void updateComment(String content) {
        this.content = content;
    }
}
