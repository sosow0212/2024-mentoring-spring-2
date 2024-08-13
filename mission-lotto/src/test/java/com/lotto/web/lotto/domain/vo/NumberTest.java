package com.lotto.web.lotto.domain.vo;

import com.lotto.web.lotto.domain.CreateRandomNumber;
import com.lotto.web.lotto.domain.FakeRandomNumberGenerator.FakeRandomNumberGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NumberTest {

    @Test
    @DisplayName("Number 올바른 값 반환 테스트")
    void getNumber() {
        // given
        CreateRandomNumber createRandomNumber = new FakeRandomNumberGenerator();
        Number number = new Number(createRandomNumber.generateRandomNumber());
        int expected = 4;

        // when
        int actual = number.getNumber();

        // then
        assertEquals(expected, actual);
    }
}
