package com.board.member;

import com.board.member.repository.MemberRepository;
import com.board.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;

    @Test
    @DisplayName("중복되지 않는 새로운 유저를 생성한다.")
    void signUp_newMember() {

    }

    @Test
    @DisplayName("닉네임이 중복되는 새로운 유저를 생성한다.")
    void signUp_duplicate_nickName_member() {

    }

    @Test
    @DisplayName("아이디가 중복되는 새로운 유저를 생성한다.")
    void signUp_duplicate_loginId_member() {

    }

    @Test
    @DisplayName("유저 정보를 업데이트 한다.")
    void update_member() {

    }

    @Test
    @DisplayName("유저 정보를 조회한다.")
    void find_member() {

    }

    @Test
    @DisplayName("유저 정보를 삭제한다.")
    void delete_member() {

    }
}
