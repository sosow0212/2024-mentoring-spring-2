package com.lotto.controller.dto;


import java.util.List;

public record UserResultResponse(
        List<Integer> lottoNumber,
        boolean result
) {
}
