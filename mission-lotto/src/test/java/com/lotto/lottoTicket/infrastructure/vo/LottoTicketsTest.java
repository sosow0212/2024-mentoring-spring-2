package com.lotto.lottoTicket.infrastructure.vo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LottoTicketsTest {

    private final LottoGenerator lottoGenerator = new RandomLottoGenerator();

    @Test
    public void 로또티켓생성테스트() {

        //given
        List<LottoTicket> tickets = new ArrayList<>();
        LottoTickets lottoTickets = new LottoTickets(lottoGenerator, tickets);

        //when
        List<LottoTicket> generatedTickets = lottoTickets.generateLottoTicket(5);

        //then
        assertAll(
                () -> assertEquals(5, generatedTickets.size()),
                () -> assertTrue(generatedTickets.stream().allMatch(ticket -> ticket.getNumbers().size() == 6))
        );

    }
}