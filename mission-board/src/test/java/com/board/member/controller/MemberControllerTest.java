package com.board.member.controller;

import com.board.member.controller.dto.MemberRequest;
import com.board.member.controller.dto.MemberResponse;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    private MemberResponse memberResponse;
    private MemberRequest memberRequest;

    @BeforeEach
    void setUp() {
        memberResponse = new MemberResponse(1L, "jay", "jj", "aaa", "bbb");
        memberRequest = new MemberRequest("jay", "jj", "aaa", "bbb");
    }

    @DisplayName("회원가입 테스트.")
    @Test
    void signup() throws Exception {
        //given
        Member newMember = new Member(1L, "jay", "jj", "aaa", "bbb");
        when(memberService.signUp(memberRequest)).thenReturn(newMember);

        //when&then
        mockMvc.perform(post("/api/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(memberRequest)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/api/members/1"))
                .andDo(print());
    }
}
