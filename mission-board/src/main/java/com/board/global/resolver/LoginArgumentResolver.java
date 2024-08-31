package com.board.global.resolver;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.board.global.annotation.Login;
import com.board.global.resolver.exception.ExistTokenException;
import com.board.global.resolver.exception.TokenTimeException;
import com.board.global.resolver.exception.TokenVerifyException;
import com.board.login.domain.Jwt;
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

    private final Jwt jwt;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean isLoginAnnotation = parameter.hasParameterAnnotation(Login.class);
        boolean isMemberIdType = Long.class.isAssignableFrom(parameter.getParameterType());
        return isLoginAnnotation && isMemberIdType;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        String tokenHeader = Optional.ofNullable(request.getHeader("Authorization"))
                .orElseThrow(ExistTokenException::new);
        String token = tokenHeader.substring(7);
        try {
            DecodedJWT decodedJWT = jwt.verifyJwtToken(token);
            Long memberId = decodedJWT.getClaim("memberId").asLong();
            return Optional.of(memberId)
                    .orElseThrow(ExistTokenException::new);
        } catch (TokenExpiredException e) {
            throw new TokenTimeException();
        } catch (JWTVerificationException e) {
            throw new TokenVerifyException();
        }
    }
}
