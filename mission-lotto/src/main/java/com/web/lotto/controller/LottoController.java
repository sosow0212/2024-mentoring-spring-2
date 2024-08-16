package com.web.lotto.controller;

import com.web.lotto.controller.dto.BuyTicketsRequest;
import com.web.lotto.controller.dto.BuyTicketsResponse;
import com.web.lotto.controller.dto.LottoResponse;
import com.web.lotto.controller.dto.WinningNumbersResponse;

import com.web.lotto.controller.message.SettingWinningNumbers;

import com.web.lotto.domain.entity.Lotto;

import com.web.lotto.service.LottoService;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/lottos")
public class LottoController {

    private final LottoService lottoService;

    public LottoController(final LottoService lottoService) {
        this.lottoService = lottoService;
    }

    @PostMapping("/buying")

    public ResponseEntity<BuyTicketsResponse> buyTickets(@RequestBody BuyTicketsRequest request) {
        lottoService.buyLotto(request.userId(), request.ticketCount());
        BuyTicketsResponse response = new BuyTicketsResponse(request.userId(), request.ticketCount());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/settingWinningNumbers")

    public ResponseEntity<String> setWinningNumbers(@RequestBody WinningNumbersResponse winningNumbersResponse) {
        lottoService.getWinningLottoTickets(winningNumbersResponse.winningNumbers());
        return ResponseEntity.ok(new SettingWinningNumbers().toString());
    }

    @GetMapping("/tickets")
    public ResponseEntity<List<LottoResponse>> getLottoTickets() {

        List<Lotto> lottoTickets = lottoService.getLottoTickets();

        List<LottoResponse> lottoResponses = lottoTickets
                .stream()
                .map(lotto -> new LottoResponse(
                        lotto.getId(),
                        lotto.getLottoTickets(),
                        lotto.getLottoTickets(),
                        lotto.getUser(),
                        lottoService.calculateWinnings(lotto)))
                .toList();

        return ResponseEntity.ok(lottoResponses);
    }

    @GetMapping("/tickets/{userId}")

    public ResponseEntity<LottoResponse> getLottoTicketsByUser(@PathVariable Long userId) {

        Lotto lotto = lottoService.getLottoTicketsById(userId);

        LottoResponse lottoResponses = new LottoResponse(
                lotto.getId(),
                lotto.getLottoTickets(),
                lotto.getWinningLottoTicket(),
                lotto.getUser(),
                lottoService.calculateWinnings(lotto));

        return ResponseEntity.ok(lottoResponses);
    }

}