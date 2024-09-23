package com.board.login.domain;

import jakarta.servlet.http.HttpSession;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionStorage {

    private final static Map<Long, HttpSession> sessions = new ConcurrentHashMap<>();

    public static void setSession(Long memberId, HttpSession httpSession) {
        sessions.put(memberId, httpSession);
    }

    public static void deleteSession(Long memberId) {
        sessions.remove(memberId);
    }
}
