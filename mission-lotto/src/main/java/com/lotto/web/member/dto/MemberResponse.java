package com.lotto.web.member.dto;

public record MemberResponse(Long id, String name, int lottoTicket, int winning) {
}
