package com.board.member.service;

import com.board.member.controller.dto.MemberRequest;
import com.board.member.domain.Member;
import com.board.member.mapper.MemberMapper;
import com.board.member.repository.MemberRepository;
import com.board.member.service.exception.ExistMemberLoginIdException;
import com.board.member.service.exception.ExistMemberNickNameException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;

    private MemberRequest memberRequest;

    @BeforeEach
    void setup() {
        memberRequest = new MemberRequest("jay", "jj", "aaa", "sss");
    }


    @Test
    @DisplayName("중복되지 않는 새로운 유저를 생성한다.")
    void signUp_newMember() {
        //given
        when(memberRepository.existsByMemberLoginId(any())).thenReturn(false);
        when(memberRepository.existsByMemberNickName(any())).thenReturn(false);

        //when
        Member result = memberService.signUp(memberRequest);

        //then
        assertEquals("aaa", result.getMemberLoginId());
        verify(memberRepository).save(any(Member.class));
    }

    @Test
    @DisplayName("닉네임이 중복되는 새로운 유저를 생성한다.")
    void signUp_duplicate_nickName_member() {
        //given
        when(memberRepository.existsByMemberNickName(any())).thenReturn(true);

        //when & then
        assertThrows(ExistMemberNickNameException.class, () -> memberService.signUp(memberRequest));
    }

    @Test
    @DisplayName("아이디가 중복되는 새로운 유저를 생성한다.")
    void signUp_duplicate_loginId_member() {
        //given
        when(memberRepository.existsByMemberLoginId(any())).thenReturn(true);

        //when & then
        assertThrows(ExistMemberLoginIdException.class, () -> memberService.signUp(memberRequest));
    }

    @Test
    @DisplayName("유저 정보를 업데이트 한다.(닉네임 변경)")
    void update_member() {
        //given
        MemberRequest memberUpdateRequest = new MemberRequest("jay", "jay", "aaa", "sss");
        Member existMember = MemberMapper.toMember(memberRequest);
        when(memberRepository.existsByMemberLoginId(any())).thenReturn(false);
        when(memberRepository.existsByMemberNickName(any())).thenReturn(false);
        when(memberRepository.findById(anyLong())).thenReturn(Optional.of(existMember));

        //when
        Member result = memberService.updateMember(memberUpdateRequest, 1L);

        //then
        assertEquals("jay", result.getMemberNickName());
    }

    @Test
    @DisplayName("유저 정보를 조회한다.")
    void find_member() {
        //given
        Member existMember = MemberMapper.toMember(memberRequest);
        when(memberRepository.findById(anyLong())).thenReturn(Optional.of(existMember));

        //when
        Member result = memberService.findMember(1L);

        //then
        assertEquals("jj", result.getMemberNickName());
    }

    @Test
    @DisplayName("유저 정보를 삭제한다.")
    void delete_member() {
        //given
        Member existMember = MemberMapper.toMember(memberRequest);
        when(memberRepository.findById(anyLong())).thenReturn(Optional.of(existMember));

        //when
        Member result = memberService.deleteMember(1L);

        //then
        assertEquals("jj", result.getMemberNickName());
        verify(memberRepository).delete(any(Member.class));
    }
}
