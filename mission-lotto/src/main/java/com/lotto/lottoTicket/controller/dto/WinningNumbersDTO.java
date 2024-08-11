package com.lotto.lottoTicket.controller.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record WinningNumbersDTO(@NotNull List<Integer> winningNumbers) {

    @Override
    public List<Integer> winningNumbers() {
        return winningNumbers;
    }

    public WinningNumbersDTO(final List<Integer> winningNumbers) {
        this.winningNumbers = winningNumbers;
    }
}
