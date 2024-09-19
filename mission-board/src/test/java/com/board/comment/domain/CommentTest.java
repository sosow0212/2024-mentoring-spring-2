package com.board.comment.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CommentTest {

    private Comment comment;

    @BeforeEach
    void setUp() {
        comment = new Comment(1L, 1L, "aa");
    }

    @DisplayName("댓글 업데이트 테스트")
    @Test
    void update_comment() {
        //given
        String update = "bb";

        //when
        comment.updateComment(update);

        //then
        assertEquals("bb", comment.getContent());
    }
}
