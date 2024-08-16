package com.web.lotto.controller.dto;

import com.web.user.domain.entity.User;

import java.util.List;

public record LottoResponse(
        Long id
        , List<Integer> lottoTickets
        , List<Integer> winningLottoTicket
        , User user
        , int winningMoney) {
}