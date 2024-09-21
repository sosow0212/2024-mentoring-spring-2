package com.board.login.controller;

import com.board.global.resolver.LoginArgumentResolver;
import com.board.login.config.WebConfig;
import com.board.login.controller.dto.LoginRequest;
import com.board.login.service.CookieLoginService;
import com.board.member.domain.Member;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = CookieLoginController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {WebConfig.class, LoginArgumentResolver.class}))
public class CookieLoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CookieLoginService cookieLoginService;

    @DisplayName("쿠키 로그인 테스트.")
    @Test
    void cookie_login() throws Exception {
        //given
        LoginRequest loginRequest = new LoginRequest("aaa", "bbb");
        Member loginMember = new Member(1L, "jay", "jj", "aaa", "bbb");
        when(cookieLoginService.login(loginRequest)).thenReturn(loginMember);

        //when&then
        mockMvc.perform(post("/api/members/cookie/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @DisplayName("쿠키 로그아웃 테스트.")
    @Test
    void cookie_logout() throws Exception {
        //given
        Member logoutMember = new Member(1L, "jay", "jj", "aaa", "bbb");
        Cookie cookie = new Cookie("memberId", String.valueOf(logoutMember.getId()));
        when(cookieLoginService.findMemberByCookie(Mockito.any(HttpServletRequest.class))).thenReturn(logoutMember);

        //when&then
        mockMvc.perform(post("/api/members/cookie/logout")
                        .cookie(cookie))
                .andExpect(status().isOk())
                .andExpect(cookie().value("memberId", ""))
                .andDo(print());
    }
}
