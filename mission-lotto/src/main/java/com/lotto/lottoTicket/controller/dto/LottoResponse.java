package com.lotto.lottoTicket.controller.dto;

import com.lotto.lottoTicket.infrastructure.vo.LottoTicket;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class LottoResponse {

    private final Long userId;
    private final String userName;
    private final List<List<Integer>> numbers;
    private final int winnings;

    public LottoResponse(Long userId, String userName, List<LottoTicket> tickets, int winnings) {
        this.userId = userId;
        this.userName = userName;
        this.numbers = tickets
                .stream()
                .map(LottoTicket::getNumbers)
                .collect(Collectors.toList());
        this.winnings = winnings;
    }

}