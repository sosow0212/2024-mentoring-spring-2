package com.board.global.interceptor;

import com.board.global.exception.exceptions.CustomException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class LoginInterceptorTest {


    private LoginInterceptor loginInterceptor;

    @BeforeEach
    void setUp() {
        loginInterceptor = new LoginInterceptor();
    }

    @DisplayName("토큰이 존재하지 않을 경우.")
    @Test
    void preHandle_not_exist_token() {
        //given
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        //when&then
        assertThrows(CustomException.class, () -> loginInterceptor.preHandle(request, response, new Object()));
    }

    @DisplayName("토큰 헤더 네이밍이 잘못된 경우.")
    @Test
    void preHandle_wrong_token_header() throws Exception {
        //given
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Authorization", "Bbb 111");
        MockHttpServletResponse response = new MockHttpServletResponse();

        //when
        boolean result = loginInterceptor.preHandle(request, response, new Object());

        //then
        assertFalse(result);
    }

    @DisplayName("올바른 토큰을 발급했을 경우.")
    @Test
    void preHandle_exist_token() throws Exception {
        //given
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Authorization", "Bearer valid_token");
        MockHttpServletResponse response = new MockHttpServletResponse();

        //when
        boolean result = loginInterceptor.preHandle(request, response, new Object());

        //then
        assertTrue(result);
    }
}
