package com.web.lotto.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.lotto.controller.dto.BuyTicketsRequest;
import com.web.lotto.controller.dto.BuyTicketsResponse;
import com.web.lotto.controller.dto.LottoResponse;
import com.web.lotto.controller.dto.WinningNumbersResponse;

import com.web.lotto.controller.message.SettingWinningNumbers;

import com.web.lotto.domain.entity.Lotto;

import com.web.lotto.service.LottoService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lotto")
public class LottoController {

    private final LottoService lottoService;

    private final ObjectMapper objectMapper;

    @PostMapping("/lottoTicket")
    public ResponseEntity<BuyTicketsResponse> buyTickets(@RequestBody BuyTicketsRequest request) {
        return getBuyTicketsResponseEntity(request);
    }

    @PostMapping("/settingWinningNumbers")
    public ResponseEntity<String> setWinningNumbers(@RequestBody WinningNumbersResponse winningNumbersResponse) {
        return getWinningResponseEntity(winningNumbersResponse);
    }

    @GetMapping("/tickets")
    public ResponseEntity<List<LottoResponse>> getLottoTickets() {
        return getListResponseEntity();
    }

    @GetMapping("/tickets/{userId}")
    public ResponseEntity<LottoResponse> getLottoTicketsByUser(@PathVariable Long userId) {
        Lotto lotto = lottoService.getLottoTicketsById(userId);
        LottoResponse lottoResponses = expressingLottoResponse(lotto);
        return ResponseEntity.ok(lottoResponses);
    }

    private List<LottoResponse> toLottoResponses(final List<Lotto> lottoTickets) {
        return lottoTickets
                .stream()
                .map(lotto -> new LottoResponse(
                        lotto.getId(),
                        lotto.getLottoTickets(),
                        lotto.getWinningLottoTicket(),
                        lotto.getUser(),
                        lottoService.calculateWinnings(lotto)))
                .toList();
    }

    private ResponseEntity<List<LottoResponse>> getListResponseEntity() {
        List<Lotto> lottoTickets = lottoService.getLottoTickets();
        List<LottoResponse> lottoResponses = toLottoResponses(lottoTickets);
        return ResponseEntity.ok(lottoResponses);
    }

    private ResponseEntity<BuyTicketsResponse> getBuyTicketsResponseEntity(final BuyTicketsRequest request) {
        lottoService.buyLotto(request.userId(), request.ticketCount());
        BuyTicketsResponse response = new BuyTicketsResponse(request.userId(), request.ticketCount());
        return ResponseEntity.ok(response);
    }

    private LottoResponse expressingLottoResponse(final Lotto lotto) {
        return new LottoResponse(
                lotto.getId(),
                lotto.getLottoTickets(),
                lotto.getWinningLottoTicket(),
                lotto.getUser(),
                lottoService.calculateWinnings(lotto));
    }

    private ResponseEntity<String> getWinningResponseEntity(final WinningNumbersResponse winningNumbersResponse) {
        lottoService.getWinningLottoTickets(winningNumbersResponse.winningNumbers());
        try {
            String jsonString = objectMapper.writeValueAsString(new SettingWinningNumbers());
            return ResponseEntity.ok(jsonString);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
