package com.web.lotto.domain.vo;

import com.web.lotto.infrastructure.LottoPrice;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WinningLottoCalculator {

    private final List<Integer> winningNumbers;

    public WinningLottoCalculator(final List<Integer> winningNumbers) {
        this.winningNumbers = winningNumbers;
    }

    public int calculateTotalWinnings(List<Integer> lotto, List<Integer> allLottoTickets) {
        return lotto
                .stream()
                .mapToInt(ticket -> {
                    int matchingCount = getMatchingCount(lotto);
                    LottoPrice prize = LottoPrice.getPrizeByCount(matchingCount);
                    return prize.getPrizeAmount() * calculateWinningCheckNumbers(lotto, allLottoTickets)
                            .getOrDefault(prize, 0L).intValue();
                })
                .sum();
    }

    public Map<LottoPrice, Long> calculateWinningCheckNumbers(List<Integer> lotto, List<Integer> allLottoTickets) {
        return allLottoTickets.stream()
                .map(ticket -> LottoPrice.getPrizeByCount(getMatchingCount(lotto)))
                .collect(Collectors.groupingBy(prize -> prize, Collectors.counting()));
    }

    public int getMatchingCount(List<Integer> lotto) {
        long matchingCount = lotto
                .stream()
                .filter(winningNumbers::contains)
                .count();
        return (int) matchingCount;
    }

}