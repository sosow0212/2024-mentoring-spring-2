package com.lotto.lottoTicket.infrastructure.vo;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RandomLottoGeneratorTest {

    private final LottoGenerator lottoGenerator = new RandomLottoGenerator();

    @Test
    public void 로또생성테스트() {

        //given
        List<Integer> numbers = lottoGenerator.generateRandomNumber();

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
