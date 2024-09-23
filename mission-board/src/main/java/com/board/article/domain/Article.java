package com.board.article.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@AllArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long memberId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    public Article(Long memberId, String title, String content) {
        this.memberId = memberId;
        this.title = title;
        this.content = content;
    }

    public void updateArticle(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
