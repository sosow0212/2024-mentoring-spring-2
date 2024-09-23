package com.board.login.controller;

import com.board.global.resolver.LoginArgumentResolver;
import com.board.login.config.WebConfig;
import com.board.login.controller.dto.LoginRequest;
import com.board.login.service.SessionLoginService;
import com.board.member.domain.Member;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = SessionLoginController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {WebConfig.class, LoginArgumentResolver.class}))
public class SessionLoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SessionLoginService sessionLoginService;

    @DisplayName("세션 로그인 테스트.")
    @Test
    void session_login() throws Exception {
        //given
        LoginRequest loginRequest = new LoginRequest("aaa", "bbb");
        Member loginMember = new Member(1L, "jay", "jj", "aaa", "bbb");
        when(sessionLoginService.login(loginRequest)).thenReturn(loginMember);

        //when&then
        mockMvc.perform(post("/api/members/session/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @DisplayName("세션 로그아웃 테스트.")
    @Test
    void session_logout() throws Exception {
        //given
        Member logoutMember = new Member(1L, "jay", "jj", "aaa", "bbb");
        MockHttpServletRequest request = new MockHttpServletRequest();
        HttpSession session = request.getSession();
        assert session != null;
        session.setAttribute("memberId", logoutMember.getId());
        when(sessionLoginService.findMemberBySession(Mockito.any())).thenReturn(logoutMember);

        //when&then
        mockMvc.perform(post("/api/members/session/logout")
                        .session((MockHttpSession) session))
                .andExpect(status().isOk())
                .andExpect(request().sessionAttribute("memberId", (Object) null))
                .andDo(print());
    }
}
