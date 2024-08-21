package com.board.login.domain;

import jakarta.servlet.http.Cookie;

import java.util.HashMap;
import java.util.Map;

public class CookieStorage {

    private final static Map<Long, Cookie> cookies = new HashMap<>();

    public static void setCookie(Long memberId, Cookie cookie) {
        cookies.put(memberId, cookie);
    }

    public static void deleteCookie(Long memberId) {
        cookies.remove(memberId);
    }
}
