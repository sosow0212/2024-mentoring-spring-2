package com.web.lotto.domain.vo;

import com.web.lotto.infrastructure.RandomGeneratorLotto;

import java.util.ArrayList;
import java.util.List;

public class LottoTickets {

    private final RandomGeneratorLotto randomGeneratorLotto;

    public LottoTickets(final RandomGeneratorLotto randomGeneratorLotto) {
        this.randomGeneratorLotto = randomGeneratorLotto;
    }

    public List<List<Integer>> generateLottoTicketInventory(int ticketCount) {
        List<List<Integer>> tickets = new ArrayList<>();
        generateLottoTickets(ticketCount, tickets);
        return tickets;
    }

    private void generateLottoTickets(final int ticketCount, final List<List<Integer>> tickets) {
        for (int i = 0; i < ticketCount; i++) {
            tickets.add(randomGeneratorLotto.generateRandomNumber());
        }
    }
}
