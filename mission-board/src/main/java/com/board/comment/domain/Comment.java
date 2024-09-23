package com.board.comment.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long memberId;

    @Column
    private Long articleId;

    @Column(nullable = false)
    private String content;

    public Comment(Long memberId, Long articleId, String content) {
        this.memberId = memberId;
        this.articleId = articleId;
        this.content = content;
    }

    public void updateComment(String content) {
        this.content = content;
    }
}
