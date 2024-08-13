package com.lotto.web.lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LottoRankTest {

    @Test
    @DisplayName("LottoRank 반환값 테스트")
    void getCount() {
        // given
        List<Integer> lottoAnswer = List.of(1,2,3,4,5,6);
        List<Integer> lotto = List.of(1,2,3,8,9,10);
        LottoRank lottoRank = new LottoRank(lottoAnswer, lotto);
        int expected = 3;

        // when
        int actual = lottoRank.getCount();

        // then
        assertEquals(expected, actual);
    }
}
