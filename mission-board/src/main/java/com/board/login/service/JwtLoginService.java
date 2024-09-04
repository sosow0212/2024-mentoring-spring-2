package com.board.login.service;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.board.login.service.exception.ExistTokenException;
import com.board.login.service.exception.TokenTimeException;
import com.board.login.service.exception.TokenVerifyException;
import com.board.login.controller.dto.LoginRequest;
import com.board.login.service.exception.ExistMemberLoginIdException;
import com.board.member.domain.Member;
import com.board.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JwtLoginService implements LoginService {

    private final MemberRepository memberRepository;
    private final JwtToken jwtToken;

    @Override
    public Member login(LoginRequest loginRequest) {
        Member member = memberRepository.findByMemberLoginId(loginRequest.memberLoginId())
                .orElseThrow(ExistMemberLoginIdException::new);
        member.checkPassword(loginRequest.memberPassword());
        return member;
    }

    public String createJwtToken(Long memberId) {
        return jwtToken.createToken(memberId);
    }

    public DecodedJWT verifyJwtToken(String token) {
        return jwtToken.verifyToken(token);
    }

    public Long verifyAndExtractJwtToken(String token) {
        try {
            return validateTokenExist(token);
        } catch (TokenExpiredException e) {
            throw new TokenTimeException();
        } catch (JWTVerificationException e) {
            throw new TokenVerifyException();
        }
    }

    private Long validateTokenExist(String token) {
        return Optional.of(extractJwtToken(token))
                .orElseThrow(ExistTokenException::new);
    }

    private Long extractJwtToken(String token) {
        return verifyJwtToken(token).getClaim("memberId")
                .asLong();
    }
}
