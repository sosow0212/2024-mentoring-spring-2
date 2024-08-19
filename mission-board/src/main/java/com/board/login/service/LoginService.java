package com.board.login.service;

import com.board.login.controller.dto.LoginRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface LoginService {

    String login(HttpServletResponse response, LoginRequest loginRequest);

    void logout(HttpServletResponse response, HttpServletRequest request);
}
