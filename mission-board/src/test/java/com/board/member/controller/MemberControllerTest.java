package com.board.member.controller;

import com.board.global.resolver.LoginArgumentResolver;
import com.board.member.controller.dto.MemberRequest;
import com.board.member.domain.Member;
import com.board.member.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberController.class)
public class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MemberService memberService;

    @MockBean
    private LoginArgumentResolver loginArgumentResolver;

    private MemberRequest memberRequest;
    private Member member;
    private String token;
    private Long memberId;

    @BeforeEach
    void setUp() {
        memberId = 1L;
        token = "jwt";
        member = new Member(1L, "jay", "jj", "aaa", "bbb");
        memberRequest = new MemberRequest("jay", "jj", "aaa", "bbb");
    }

    @DisplayName("회원가입 테스트.")
    @Test
    void signup() throws Exception {
        //given
        when(memberService.signUp(memberRequest)).thenReturn(member);

        //when&then
        mockMvc.perform(post("/api/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(memberRequest)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/api/members/1"))
                .andDo(print());
    }

    @DisplayName("유저 조회 테스트.")
    @Test
    void show_member() throws Exception {
        //given
        when(loginArgumentResolver.supportsParameter(any())).thenReturn(true);
        when(loginArgumentResolver.resolveArgument(any(), any(), any(), any())).thenReturn(memberId);
        when(memberService.findMember(memberId)).thenReturn(member);

        //when&then
        mockMvc.perform(get("/api/members")
                        .header("Authorization", token))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @DisplayName("유저 정보 수정 테스트.")
    @Test
    void update_member() throws Exception {
        //given
        MemberRequest updateMemberRequest = new MemberRequest("j", "j", "a", "b");
        Member updatedMember = new Member("j", "j", "a", "b");
        when(loginArgumentResolver.supportsParameter(any())).thenReturn(true);
        when(loginArgumentResolver.resolveArgument(any(), any(), any(), any())).thenReturn(memberId);
        when(memberService.updateMember(updateMemberRequest, memberId)).thenReturn(updatedMember);

        //when&then
        mockMvc.perform(patch("/api/members")
                        .header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateMemberRequest)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @DisplayName("계정 삭제 테스트.")
    @Test
    void delete_member() throws Exception {
        //given
        Member deleteMember = new Member("j", "j", "a", "b");
        when(loginArgumentResolver.supportsParameter(any())).thenReturn(true);
        when(loginArgumentResolver.resolveArgument(any(), any(), any(), any())).thenReturn(memberId);
        when(memberService.deleteMember(memberId)).thenReturn(deleteMember);

        //when&then
        mockMvc.perform(delete("/api/members")
                        .header("Authorization", token))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
