package com.board.article.domain;

import com.board.member.domain.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Entity
@NoArgsConstructor
@Getter
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    @Column
    @Nullable
    private String title;

    @Column
    @Nullable
    private String content;

    public Article(Member member, @Nullable String title, @Nullable String content) {
        this.member = member;
        this.title = title;
        this.content = content;
    }

    public void updateArticle(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
