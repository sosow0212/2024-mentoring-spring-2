package com.web.lottoTicket.infrastructure.vo;

import com.web.lotto.domain.vo.LottoTicket;
import com.web.lotto.domain.vo.LottoTickets;
import com.web.lotto.infrastructure.RandomGeneratorLotto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LottoTicketsTest {

    private final RandomGeneratorLotto randomGeneratorLotto = new RandomGeneratorLotto();

    @Test
    public void 로또티켓생성테스트() {

        //given
        List<LottoTicket> tickets = new ArrayList<>();
        LottoTickets lottoTickets = new LottoTickets(randomGeneratorLotto);

        //when
        List<List<Integer>> generatedTickets = lottoTickets.generateLottoTicketInventory(5);

        //then
        assertAll(
                () -> assertEquals(5, generatedTickets.size()),
                () -> assertTrue(generatedTickets.stream().allMatch(ticket -> generatedTickets.size() == 6))
        );

    }
}
