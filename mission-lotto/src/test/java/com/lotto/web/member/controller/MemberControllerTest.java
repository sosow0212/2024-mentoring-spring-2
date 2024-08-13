package com.lotto.web.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lotto.web.member.dto.CreateRequest;
import com.lotto.web.member.entity.Member;
import com.lotto.web.member.entity.MemberLotto;
import com.lotto.web.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MemberController.class)
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private MemberService memberService;

    @Test
    @DisplayName("유저 생성하기.")
    void createUser() throws Exception {
        // given
        CreateRequest createRequest = new CreateRequest("jay", 5000);
        Member member = new Member("jay", 5000);
        given(memberService.createMember(createRequest)).willReturn(member);

        // then
        mockMvc.perform(post("/api/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/api/members/1"));

        verify(memberService).createMember(createRequest);
    }

    @Test
    @DisplayName("모든 유저 보여주기.")
    void showUser() throws Exception {
        // given
        Member member = new Member("jay", 5000);
        List<MemberLotto> memberLottos = List.of(new MemberLotto(member));
        given(memberService.findAllUsers()).willReturn(memberLottos);

        // then
        mockMvc.perform(get("/api/members")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(memberService).findAllUsers();
    }
}
