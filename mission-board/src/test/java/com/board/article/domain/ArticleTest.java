package com.board.article.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ArticleTest {

    private Article article;

    @BeforeEach
    void setUp() {
        article = new Article(1L, "aa", "bb");
    }

    @DisplayName("게시글 업데이트 테스트.")
    @Test
    void update_article() {
        //given
        String updateTitle = "a";
        String updateContent = "b";

        //when
        article.updateArticle(updateTitle, updateContent);

        //then
        assertEquals(updateTitle, article.getTitle());
        assertEquals(updateContent, article.getContent());
    }
}
