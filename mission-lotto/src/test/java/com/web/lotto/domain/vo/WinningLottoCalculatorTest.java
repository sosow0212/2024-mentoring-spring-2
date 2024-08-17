package com.web.lotto.domain.vo;

import com.web.lotto.infrastructure.LottoPrice;
import com.web.lotto.infrastructure.RandomGeneratorLotto;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WinningLottoCalculatorTest {

    @Test
    void 숫자_계산테스트() {
        // given
        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        WinningLottoCalculator calculator = new WinningLottoCalculator(winningNumbers);
        List<Integer> lottoTicket = Arrays.asList(1, 2, 3, 7, 8, 9);

        // when
        int matchingCount = calculator.getMatchingCount(lottoTicket);

        // then
        assertEquals(3, matchingCount);

    }

    @Test
    void 티켓_생성_테스트() {
        // given
        RandomGeneratorLotto generator = new RandomGeneratorLotto();
        LottoTickets lottoTickets = new LottoTickets(generator);
        int ticketCount = 5;

        // when
        List<List<Integer>> tickets = lottoTickets.generateLottoTickets(ticketCount);

        // then
        assertEquals(ticketCount, tickets.size());
        tickets.forEach(ticket -> assertEquals(6, ticket.size()));

    }

    @Test
    void 랜덤_숫자_생성_테스트() {
        // given
        GeneratorLotto generatorLotto = new RandomGeneratorLotto();

        // when
        List<Integer> randomNumbers = generatorLotto.generateRandomNumber();

        // then
        assertEquals(6, randomNumbers.size());
        randomNumbers.forEach(number -> assertTrue(number >= 1 && number <= 45));
    }

}
