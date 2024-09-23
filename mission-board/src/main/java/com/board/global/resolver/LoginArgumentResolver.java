package com.board.global.resolver;

import com.board.global.annotation.Login;
import com.board.login.service.exception.ExistTokenException;
import com.board.login.service.JwtLoginService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LoginArgumentResolver implements HandlerMethodArgumentResolver {

    private static final String TOKEN_HEADER_NAME = "Authorization";

    private final JwtLoginService jwtLoginService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean isLoginAnnotation = parameter.hasParameterAnnotation(Login.class);
        boolean isMemberIdType = Long.class.isAssignableFrom(parameter.getParameterType());
        return isLoginAnnotation && isMemberIdType;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        String tokenHeader = Optional.ofNullable(request.getHeader(TOKEN_HEADER_NAME))
                .orElseThrow(ExistTokenException::new);
        String token = tokenHeader.substring(7);
        return jwtLoginService.verifyAndExtractJwtToken(token);
    }
}
