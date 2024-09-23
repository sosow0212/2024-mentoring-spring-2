package com.board.global.resolver;

import com.board.login.service.JwtLoginService;
import com.board.login.service.exception.ExistTokenException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.MethodParameter;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LoginArgumentResolverTest {

    @Mock
    private JwtLoginService jwtLoginService;

    private LoginArgumentResolver loginArgumentResolver;
    private MethodParameter methodParameter;

    @BeforeEach
    void setUp(){
        loginArgumentResolver = new LoginArgumentResolver(jwtLoginService);
        methodParameter =  mock(MethodParameter.class);
    }

    @DisplayName("resolverArgument 올바르게 동작하는 테스트.")
    @Test
    void resolveArgument_have_token() throws Exception {
        //given
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Authorization", "Bearer 11");
        NativeWebRequest webRequest = new ServletWebRequest(request);
        when(jwtLoginService.verifyAndExtractJwtToken("11")).thenReturn(1L);

        //when
        Long memberId = (Long) loginArgumentResolver.resolveArgument(methodParameter, null, webRequest, null);

        // then
        assertEquals(1L, memberId);
        verify(jwtLoginService).verifyAndExtractJwtToken("11");
    }

    @DisplayName("resolveArgument 토큰 없을 때 테스트.")
    @Test
    void resolveArgument_do_not_have_token() {
        //given
        MockHttpServletRequest request = new MockHttpServletRequest();
        NativeWebRequest webRequest = new ServletWebRequest(request);

        // when & then
        assertThrows(ExistTokenException.class, ()-> loginArgumentResolver.resolveArgument(methodParameter, null, webRequest, null));
    }
}
