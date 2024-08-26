package com.board.login.domain;

import jakarta.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

public class SessionStorage {

    private final static Map<Long, HttpSession> sessions = new HashMap<>();

    public static void setSession(Long memberId, HttpSession httpSession) {
        sessions.put(memberId, httpSession);
    }

    public static void deleteSession(Long memberId) {
        sessions.remove(memberId);
    }
}
