package com.lotto.lottoTicket.infrastructure;

import lombok.Getter;

@Getter
public enum LottoPrice {
    FIRST_PRIZE(2000000),
    SECOND_PRIZE(1000000),
    THIRD_PRIZE(500000),
    FOURTH_PRIZE(100000),
    FIFTH_PRIZE(50000),
    NO_PRIZE(0);

    private final int price;

    LottoPrice(int price) {
        this.price = price;
    }

}