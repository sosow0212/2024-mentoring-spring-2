package com.web.lotto.infrastructure;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum LottoPrice {
    FIRST_PRIZE(6, 2000000),
    SECOND_PRIZE(5, 1000000),
    THIRD_PRIZE(4, 500000),
    FOURTH_PRIZE(3, 100000),
    FIFTH_PRIZE(2, 50000),
    NO_PRIZE(1, 0);

    private final int count;
    private final int prizeAmount;

    public static LottoPrice getPrizeByCount(int matchingCount) {
        return Arrays.stream(values())
                .filter(prize -> prize.count == matchingCount)
                .findFirst()
                .orElse(NO_PRIZE);
    }

}
