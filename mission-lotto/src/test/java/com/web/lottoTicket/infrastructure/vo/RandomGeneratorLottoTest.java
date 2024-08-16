package com.web.lottoTicket.infrastructure.vo;

import com.web.lotto.infrastructure.RandomGeneratorLotto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RandomGeneratorLottoTest {

    private final RandomGeneratorLotto generatorLotto = new RandomGeneratorLotto();

    @Test
    public void 로또생성테스트() {

        //given
        List<Integer> numbers = generatorLotto.generateRandomNumber();

        //when&then
        assertAll(
                () -> assertEquals(6, numbers.size()),
                () -> assertTrue(numbers.stream().allMatch(num -> num >= 1 && num <= 45)),
                () -> assertEquals(numbers.size(), numbers
                        .stream()
                        .distinct()
                        .count())
        );
    }
}
