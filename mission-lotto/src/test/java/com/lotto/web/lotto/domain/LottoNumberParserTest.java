package com.lotto.web.lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LottoNumberParserTest {

    @Test
    @DisplayName("LottoNumberParser 올바른 값 반환 테스트")
    void getLottoNumber() {
        // given
        String lottoNumber = "[1, 2, 3, 4, 5, 6]";
        LottoNumberParser lottoNumberParser = new LottoNumberParser(lottoNumber);
        List<Integer> expected = List.of(1,2,3,4,5,6);

        // when
        List<Integer> actual = lottoNumberParser.getLottoNumber();

        // then
        assertEquals(expected, actual);
    }
}
