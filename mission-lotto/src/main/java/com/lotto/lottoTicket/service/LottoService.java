package com.lotto.lottoTicket.service;

import com.lotto.lottoTicket.domain.entity.Lotto;
import com.lotto.lottoTicket.domain.repository.LottoRepository;
import com.lotto.lottoTicket.infrastructure.vo.LottoTicket;
import com.lotto.lottoTicket.infrastructure.vo.RandomLottoGenerator;
import com.lotto.lottoTicket.service.dto.BuyTicketsRequest;
import com.lotto.user.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class LottoService {

    private final LottoRepository lottoRepository;
    private final UserRepository userRepository;
    private final LottoTicket lottoTicket;
    private RandomLottoGenerator randomLottoGenerator;

    public LottoService(final LottoRepository lottoRepository, final UserRepository userRepository, final LottoTicket lottoTicket) {
        this.lottoRepository = lottoRepository;
        this.userRepository = userRepository;
        this.lottoTicket = lottoTicket;
    }

    public BuyTicketsRequest buyLotto(Long userId, int ticketCount) {

        for (int i = 0; i < ticketCount; i++) {
            Lotto lotto = Lotto
                    .builder()
                    .userId(userId)
                    .LottoTicket(lottoTicket.createLotto(randomLottoGenerator))
                    .build();
            Lotto saveLotto = lottoRepository.save(lotto);

        }
        return null;
    }

}
