package com.board.login.service;

import com.board.login.controller.dto.LoginRequest;
import com.board.member.domain.Member;

public interface LoginService {

    Member login(LoginRequest loginRequest);
}
