package com.board.login.service;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.board.login.controller.dto.LoginRequest;
import com.board.member.domain.Member;
import com.board.member.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JwtLoginServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private JwtToken jwtToken;

    @Mock
    private DecodedJWT decodedJWT;

    @InjectMocks
    private JwtLoginService jwtLoginService;

    private String token;

    @BeforeEach
    void setUp() {
        token = "Bearer jwt";
    }

    @DisplayName("로그인 테스트.")
    @Test
    void success_login() {
        //given
        LoginRequest loginRequest = new LoginRequest("aaa", "bbb");
        Member loginMember = new Member(1L, "jay", "jj", "aaa", "bbb");
        when(memberRepository.findByMemberLoginId(loginRequest.memberLoginId()))
                .thenReturn(Optional.of(loginMember));
        String expected = "aaa";


        //when
        Member member = jwtLoginService.login(loginRequest);

        //then
        assertEquals(expected, member.getMemberLoginId());
    }

    @DisplayName("토큰 생성 테스트.")
    @Test
    void create_token() {
        //given
        Long memberId = 1L;
        when(jwtToken.createToken(memberId)).thenReturn(token);
        String expected = token;

        //when
        String result = jwtLoginService.createJwtToken(memberId);

        //then
        assertEquals(expected, result);
    }

    @DisplayName("토큰 검증 테스트.")
    @Test
    void verify_token() {
        //given
        when(jwtToken.verifyToken(token)).thenReturn(decodedJWT);
        DecodedJWT expected = decodedJWT;

        //when
        DecodedJWT result = jwtLoginService.verifyJwtToken(token);

        //then
        assertEquals(expected, result);
    }

    @DisplayName("토큰 검증 후 값 뽑아내는 테스트.")
    @Test
    void verify_and_extract_token() {
        //given
        Long memberId = 1L;
        Claim claim = mock(Claim.class);
        when(jwtToken.verifyToken(token)).thenReturn(decodedJWT);
        when(decodedJWT.getClaim("memberId")).thenReturn(claim);
        when(decodedJWT.getClaim("memberId").asLong()).thenReturn(memberId);

        //when
        Long result = jwtLoginService.verifyAndExtractJwtToken(token);

        //then
        assertEquals(memberId, result);
    }
}
