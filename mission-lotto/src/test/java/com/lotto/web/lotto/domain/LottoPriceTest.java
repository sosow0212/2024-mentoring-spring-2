package com.lotto.web.lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LottoPriceTest {

    @Test
    @DisplayName("LottoPrice 반환값 테스트")
    void getLottoPrice() {
        // given
        int count = 3;
        int expected = 5000;

        // when
        int actual = LottoPrice.getLottoPrice(count);

        // then
        assertEquals(expected, actual);
    }
}
