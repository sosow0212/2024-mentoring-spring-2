package com.board.login.service;

import com.board.login.controller.dto.LoginRequest;
import com.board.member.domain.Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface LoginService {

    Member login(HttpServletResponse response, LoginRequest loginRequest);
}
