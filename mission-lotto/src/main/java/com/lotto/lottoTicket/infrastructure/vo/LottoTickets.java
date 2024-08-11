package com.lotto.lottoTicket.infrastructure.vo;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LottoTickets {

    private static final int INITIAL_NUMBER = 0;

    private final LottoGenerator lottoGenerator;
    private final List<LottoTicket> tickets;

    public LottoTickets(final LottoGenerator lottoGenerator, final List<LottoTicket> tickets) {
        this.lottoGenerator = lottoGenerator;
        this.tickets = tickets;
    }

    public List<LottoTicket> generateLottoTicket(int ticketCount) {
        generateLottoTickets(ticketCount, tickets);
        return tickets;
    }

    private void generateLottoTickets(int ticketCount, List<LottoTicket> tickets) {
        for (int i = INITIAL_NUMBER; i < ticketCount; i++) {
            List<Integer> numbers = lottoGenerator.generateRandomNumber();
            LottoTicket ticket = new LottoTicket(numbers);
            tickets.add(ticket);
        }
    }
}
