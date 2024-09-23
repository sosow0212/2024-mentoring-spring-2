package com.board.login.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class JwtTokenTest {

    private JwtToken jwtToken;

    @BeforeEach
    void setUp() {
        // 1 hour
        long expirationPeriod = 3600L;
        String secretKey = "key";
        jwtToken = new JwtToken(secretKey, expirationPeriod);
    }

    @DisplayName("토큰 생성 테스트.")
    @Test
    void create_token() {
        //when
        String tokenResult = jwtToken.createToken(1L);

        //then
        assertNotNull(tokenResult);
    }

    @DisplayName("토큰 검증 테스트.")
    @Test
    void verify_token() {
        //given
        Long memberId = 1L;
        String token = jwtToken.createToken(memberId);

        //when
        DecodedJWT result = jwtToken.verifyToken(token);

        //then
        assertEquals(memberId, result.getClaim("memberId").asLong());
    }
}
