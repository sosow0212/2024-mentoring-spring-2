package com.board.member.domain;

import com.board.member.domain.exception.ExistMemberPasswordException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class MemberTest {

    private Member member;

    @BeforeEach
    void setUp() {
        member = new Member("jay", "jj", "aaa", "bbb");
    }

    @DisplayName("유저 정보 업데이트 테스트.")
    @Test
    void update_member() {
        //given
        Long memberId = member.getId();
        Member update = new Member("j", "j", "a", "b");

        //when
        member.updateMember(memberId, update.getMemberName(), update.getMemberNickName(), update.getMemberLoginId(), update.getMemberPassword());

        //then
        assertEquals("j", member.getMemberName());
        assertEquals("j", member.getMemberNickName());
        assertEquals("a", member.getMemberLoginId());
        assertEquals("b", member.getMemberPassword());
    }

    @DisplayName("비밀번호 일치하는 경우 테스트.")
    @Test
    void check_correct_password() {
        //given
        String correctPassword = "bbb";

        //when & then
        member.checkPassword(correctPassword);
    }

    @DisplayName("비밀번호 일치하지 않는 경우 테스트.")
    @Test
    void check_wrong_password() {
        //given
        String wrongPassword = "aaa";

        //when & then
        assertThrows(ExistMemberPasswordException.class, () -> member.checkPassword(wrongPassword));
    }
}
