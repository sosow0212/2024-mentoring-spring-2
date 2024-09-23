package com.board.global.interceptor;

import com.board.global.exception.exceptions.CustomErrorCode;
import com.board.global.exception.exceptions.CustomException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Optional;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    private static final String TOKEN_HEADER_NAME = "Authorization";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String tokenHeader = Optional.ofNullable(request.getHeader(TOKEN_HEADER_NAME))
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_EXIST_TOKEN));
        return tokenHeader.startsWith("Bearer ");
    }
}
