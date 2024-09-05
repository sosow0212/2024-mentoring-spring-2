package com.missionBoard.config;

import com.missionBoard.login.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Optional;

@Component
public class Interceptor implements HandlerInterceptor {

    private final MemberService memberService;

    public Interceptor(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Optional<Object> loginUser = Optional.ofNullable(session.getAttribute("user"));
        if (loginUser.isEmpty()) {
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }
}
