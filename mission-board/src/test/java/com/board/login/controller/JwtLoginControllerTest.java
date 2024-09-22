package com.board.login.controller;

import com.board.global.resolver.LoginArgumentResolver;
import com.board.login.config.WebConfig;
import com.board.login.controller.dto.LoginRequest;
import com.board.login.service.JwtLoginService;
import com.board.member.domain.Member;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = JwtLoginController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {WebConfig.class, LoginArgumentResolver.class}))
public class JwtLoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private JwtLoginService jwtLoginService;

    @DisplayName("jwt 로그인 테스트.")
    @Test
    void jwtLogin() throws Exception {
        //given
        LoginRequest loginRequest = new LoginRequest("aaa", "bbb");
        Member loginMember = new Member(1L, "jay", "jj","aaa","bbb");
        String token = "jwt";
        when(jwtLoginService.login(loginRequest)).thenReturn(loginMember);
        when(jwtLoginService.createJwtToken(loginMember.getId())).thenReturn(token);

        //when&then
        mockMvc.perform(post("/api/members/jwt/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
