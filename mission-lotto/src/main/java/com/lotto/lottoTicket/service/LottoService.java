package com.lotto.lottoTicket.service;

import com.lotto.lottoTicket.domain.entity.Lotto;
import com.lotto.lottoTicket.domain.repository.LottoRepository;

import com.lotto.lottoTicket.infrastructure.vo.LottoTicket;
import com.lotto.lottoTicket.infrastructure.vo.LottoTickets;
import com.lotto.lottoTicket.infrastructure.LottoPrice;

import com.lotto.lottoTicket.service.exception.InsufficientBalanceException;
import com.lotto.lottoTicket.service.exception.NotFoundUserException;

import com.lotto.user.domain.entity.User;

import com.lotto.user.domain.repository.UserRepository;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LottoService {

    private final UserRepository userRepository;
    private final LottoRepository lottoRepository;
    private final LottoTickets lottoTickets;

    @Getter
    private List<Integer> winningNumbers;

    @Autowired
    public LottoService(final LottoRepository lottoRepository, final UserRepository userRepository, final LottoTickets lottoTickets) {
        this.lottoRepository = lottoRepository;
        this.userRepository = userRepository;
        this.lottoTickets = lottoTickets;
    }

    public void setWinningNumbers(List<Integer> winningNumbers) {
        this.winningNumbers = winningNumbers;
    }

    public void buyLotto(Long userId, int ticketCount) {
        User user = userRepository
                .findById(userId)
                .orElseThrow(NotFoundUserException::new);

        int totalCost = ticketCount * 1000;
        validateBalance(user, totalCost);

        List<LottoTicket> generatedTickets = lottoTickets.generateLottoTicket(ticketCount);
        saveLotto(user, generatedTickets);
        updateUser(user, ticketCount, totalCost);
    }

    public List<Lotto> getWinningLottoTickets() {
        return lottoRepository.findAll().stream()
                .filter(lotto -> lotto.getLottoTickets().stream()
                        .anyMatch(ticket -> ticket.getNumbers().equals(winningNumbers)))
                .collect(Collectors.toList());
    }

    public Map<LottoPrice, Long> calculateWinnings() {
        List<Lotto> allLottoTickets = lottoRepository.findAll();
        return allLottoTickets.stream()
                .flatMap(lotto -> lotto.getLottoTickets().stream())
                .map(ticket -> LottoPrice.getPrizeByCount(getMatchingCount(ticket)))
                .collect(Collectors.groupingBy(prize -> prize, Collectors.counting()));
    }

    private int getMatchingCount(LottoTicket ticket) {
        long matchingCount = ticket.getNumbers().stream()
                .filter(winningNumbers::contains)
                .count();
        return (int) matchingCount;
    }

    private void validateBalance(final User user, final int totalCost) {
        if (user.getBalance() < totalCost) {
            throw new InsufficientBalanceException();
        }
    }

    private void saveLotto(final User user, final List<LottoTicket> generatedTickets) {
        Lotto lotto = Lotto.builder()
                .lottoTickets(generatedTickets)
                .user(user)
                .build();
        lottoRepository.save(lotto);
    }

    private void updateUser(final User user, final int ticketCount, final int totalCost) {
        user.setBalance(user.getBalance() - totalCost);
        user.setLottoCount(user.getLottoCount() + ticketCount);
        userRepository.save(user);
    }

    public List<Lotto> getLottoTicketsByUser(Long userId) {
        return lottoRepository.findAll().stream()
                .filter(lotto -> lotto.getUser().getUserId().equals(userId))
                .collect(Collectors.toList());
    }

}