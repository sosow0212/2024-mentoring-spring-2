package com.lotto.lottoTicket.infrastructure;

import com.lotto.lottoTicket.infrastructure.vo.LottoTicket;
import com.lotto.lottoTicket.infrastructure.vo.RandomLottoGenerator;

public class LottoTickets {

    private LottoTicket lottoTicket;
    private RandomLottoGenerator randomLottoGenerator;

    public LottoTickets(final LottoTicket lottoTicket, final RandomLottoGenerator randomLottoGenerator) {
        this.lottoTicket = lottoTicket;
        this.randomLottoGenerator = randomLottoGenerator;
    }

    public void lottoTickets(int ticketCount, LottoTicket lottoTicket, RandomLottoGenerator randomLottoGenerator) {
        for (int i = 0; i < ticketCount; i++) {
            lottoTicket.createLotto(randomLottoGenerator);
        }
    }
}
