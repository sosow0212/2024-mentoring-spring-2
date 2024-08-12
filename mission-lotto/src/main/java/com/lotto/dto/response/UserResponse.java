package com.lotto.dto.response;

public record UserResponse(
        String name,
        int lottoCount,
        int prizeMoney
) {
}
