package com.board.login.domain;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class Jwt {

    private final Algorithm algorithm;
    private final long expirationPeriod;

    public Jwt(@Value("${jwt.secret}") String secretKey, @Value("${jwt.expiration-period}") long expirationPeriod) {
        this.algorithm = Algorithm.HMAC256(secretKey);
        this.expirationPeriod = expirationPeriod;
    }

    public String createJwtToken(Long memberId) {
        return JWT.create()
                .withClaim("memberId", memberId)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationPeriod * 1000L))
                .withJWTId(UUID.randomUUID().toString())
                .sign(algorithm);
    }

    public DecodedJWT verifyJwtToken(String token) {
        JWTVerifier verifier = JWT.require(algorithm)
                .build();
        return verifier.verify(token);
    }
}
