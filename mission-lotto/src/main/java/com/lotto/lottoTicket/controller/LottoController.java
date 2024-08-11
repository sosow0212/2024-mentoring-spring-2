package com.lotto.lottoTicket.controller;

import com.lotto.lottoTicket.controller.dto.BuyTicketsResponse;
import com.lotto.lottoTicket.controller.dto.LottoResponse;
import com.lotto.lottoTicket.controller.dto.WinningNumbersDTO;

import com.lotto.lottoTicket.controller.message.SettingWinningNumbers;

import com.lotto.lottoTicket.domain.entity.Lotto;

import com.lotto.lottoTicket.infrastructure.vo.LottoTicket;

import com.lotto.lottoTicket.service.LottoService;

import com.lotto.lottoTicket.controller.dto.BuyTicketsRequest;

import com.lotto.lottoTicket.infrastructure.LottoPrice;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/lottos")
public class LottoController {

    private final LottoService lottoService;

    public LottoController(final LottoService lottoService) {
        this.lottoService = lottoService;
    }

    @PostMapping("/buy")
    public ResponseEntity<BuyTicketsResponse> buyTickets(@RequestBody BuyTicketsRequest request) {
        lottoService.buyLotto(request.userId(), request.ticketCount());
        BuyTicketsResponse response = new BuyTicketsResponse(request.userId(), request.ticketCount());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/setWinningNumbers")
    public ResponseEntity<String> setWinningNumbers(@RequestBody WinningNumbersDTO winningNumbersDTO) {
        lottoService.setWinningNumbers(winningNumbersDTO.winningNumbers());
        return ResponseEntity.ok(new SettingWinningNumbers().toString());
    }

    @GetMapping("/tickets")
    public ResponseEntity<List<LottoResponse>> getLottoTickets() {
        List<Lotto> winningLottoTickets = lottoService.getWinningLottoTickets();
        Map<LottoPrice, Long> winnings = lottoService.calculateWinnings();
        List<LottoResponse> response = winningLottoTickets.stream()
                .map(lotto -> new LottoResponse(
                        lotto.getUser().getUserId(),
                        lotto.getUser().getUserName(),
                        lotto.getLottoTickets(),
                        calculateTotalWinnings(lotto, winnings)))
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/tickets/{userId}")
    public ResponseEntity<List<LottoResponse>> getLottoTicketsByUser(@PathVariable Long userId) {
        List<Lotto> userLottoTickets = lottoService.getLottoTicketsByUser(userId);
        Map<LottoPrice, Long> winnings = lottoService.calculateWinnings();
        List<LottoResponse> response = userLottoTickets.stream()
                .map(lotto -> new LottoResponse(
                        lotto.getUser().getUserId(),
                        lotto.getUser().getUserName(),
                        lotto.getLottoTickets(),
                        calculateTotalWinnings(lotto, winnings)))
                .toList();
        return ResponseEntity.ok(response);
    }

    private int calculateTotalWinnings(Lotto lotto, Map<LottoPrice, Long> winnings) {
        return lotto.getLottoTickets().stream()
                .mapToInt(ticket -> {
                    int matchingCount = getMatchingCount(ticket);
                    LottoPrice prize = LottoPrice.getPrizeByCount(matchingCount);
                    return prize.getPrizeAmount() * winnings.getOrDefault(prize, 0L).intValue();
                })
                .sum();
    }

    private int getMatchingCount(LottoTicket ticket) {
        long matchingCount = ticket.getNumbers().stream()
                .filter(lottoService.getWinningNumbers()::contains)
                .count();
        return (int) matchingCount;
    }
}
