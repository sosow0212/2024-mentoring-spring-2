package com.lotto.web.lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LottoTest {

    @Test
    @DisplayName("Lotto 반환값 사이즈 테스트")
    void getLottoNumber() {
        // given
        CreateRandomNumber createRandomNumber = new LottoRandomNumber();
        Lotto lotto = new Lotto(createRandomNumber);
        int expected = 6;

        // when
        int actual = lotto.getLotto().size();

        // then
        assertEquals(expected, actual);
    }
}
