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
        return calculateWinningsBasedOnPrice(lotto, allLottoTickets);
    }

    public Map<LottoPrice, Long> calculateWinningCheckNumbers(List<Integer> lotto, List<Integer> allLottoTickets) {
        return wrapPrize(lotto, allLottoTickets);
    }

    public int getMatchingCount(List<Integer> lotto) {
        long matchingCount = getCount(lotto);
        return (int) matchingCount;
    }

    private int calculateWinningsBasedOnPrice(final List<Integer> lotto, final List<Integer> allLottoTickets) {
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

    private Map<LottoPrice, Long> wrapPrize(final List<Integer> lotto, final List<Integer> allLottoTickets) {
        return allLottoTickets.stream()
                .map(ticket -> LottoPrice.getPrizeByCount(getMatchingCount(lotto)))
                .collect(Collectors.groupingBy(prize -> prize, Collectors.counting()));
    }

    private long getCount(final List<Integer> lotto) {
        return lotto
                .stream()
                .filter(winningNumbers::contains)
                .count();
    }

}
