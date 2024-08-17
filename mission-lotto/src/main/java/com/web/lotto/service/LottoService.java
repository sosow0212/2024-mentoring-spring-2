package com.web.lotto.service;

import com.web.lotto.domain.vo.LottoTickets;
import com.web.lotto.domain.vo.WinningLottoCalculator;

import com.web.lotto.domain.entity.Lotto;
import com.web.lotto.domain.repository.LottoRepository;

import com.web.lotto.infrastructure.RandomGeneratorLotto;
import com.web.lotto.service.exception.InsufficientBalanceException;
import com.web.user.domain.entity.User;
import com.web.user.domain.repository.UserRepository;
import com.web.user.service.exception.NotFoundUserException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LottoService {

    private final UserRepository userRepository;
    private final LottoRepository lottoRepository;

    public void buyLotto(Long userId, int ticketCount) {
        User user = userRepository.findById(userId)
                .orElseThrow(NotFoundUserException::new);
        updateUser(ticketCount, user);
        RandomGeneratorLotto randomGeneratorLotto = new RandomGeneratorLotto();
        LottoTickets lottoTicketsGenerator = new LottoTickets(randomGeneratorLotto);
        List<List<Integer>> tickets = lottoTicketsGenerator.generateLottoTicketInventory(ticketCount);
        for (List<Integer> generatedNumbers : tickets) {
            saveLotto(user, generatedNumbers);
        }
    }


    public void getWinningLottoTickets(List<Integer> winningNumbers) {
        Lotto lotto = Lotto
                .builder()
                .winningLottoTicket(winningNumbers)
                .build();
        lottoRepository.save(lotto);
    }

    private void updateUser(final int ticketCount, final User user) {
        validateBalance(user, ticketCount);
        int balance = user.getBalance() - ticketCount * 1000;
        int lottoCount = user.getLottoCount() + ticketCount;
        user.setBalance(balance);
        user.setLottoCount(lottoCount);
        userRepository.save(user);
    }


    public Lotto getLottoTicketsById(Long Id) {
        return lottoRepository
                .findById(Id)
                .orElseThrow(NotFoundUserException::new);

    }

    public List<Lotto> getLottoTickets() {
        return lottoRepository.findAll();

    }

    public int calculateWinnings(Lotto lotto) {
        WinningLottoCalculator winningLottoCalculator = new WinningLottoCalculator(lotto.getWinningLottoTicket());
        return winningLottoCalculator.calculateTotalWinnings(lotto.getLottoTickets(), lotto.getWinningLottoTicket());

    }

    private void saveLotto(final User user, final List<Integer> generatedTickets) {
        Lotto lotto = Lotto
                .builder()
                .lottoTickets(generatedTickets)
                .user(user)
                .build();
        lottoRepository.save(lotto);
    }

    private void validateBalance(final User user, final int totalCost) {
        if (user.getBalance() < totalCost) {
            throw new InsufficientBalanceException();
        }
    }

}
