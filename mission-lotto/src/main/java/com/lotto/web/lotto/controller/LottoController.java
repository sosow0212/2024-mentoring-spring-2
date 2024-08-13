package com.lotto.web.lotto.controller;

import com.lotto.web.lotto.dto.LottoRequest;
import com.lotto.web.lotto.dto.LottoResponse;
import com.lotto.web.lotto.dto.LottoResponses;
import com.lotto.web.lotto.mapper.LottoMapper;
import com.lotto.web.lotto.service.LottoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LottoController {

    private final LottoService lottoService;

    public LottoController(LottoService lottoService) {
        this.lottoService = lottoService;
    }

    @PostMapping("/lottos")
    public ResponseEntity<Void> buyLottos(@RequestBody LottoRequest lottoRequest) {
        lottoService.buyLotto(lottoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/members/{memberId}/lottos")
    public ResponseEntity<LottoResponses> showLottos(@PathVariable Long memberId) {
        List<LottoResponse> lottoResponse = lottoService.getLottos(memberId).stream()
                .map(LottoMapper::toLottoResponse)
                .toList();
        LottoResponses lottoResponses = LottoMapper.toLottoResponses(lottoResponse);
        return ResponseEntity.ok(lottoResponses);
    }

    @GetMapping(value = "/members/{memberId}/lottos", params = "order")
    public ResponseEntity<LottoResponse> showLotto(@PathVariable Long memberId, @RequestParam("order") int order) {
        List<LottoResponse> lottoResponse = lottoService.getLottos(memberId).stream()
                .map(LottoMapper::toLottoResponse)
                .toList();
        LottoResponses checkedOrderLottoResponses = LottoMapper.toCheckedOrderLottoResponses(lottoResponse, order);
        return ResponseEntity.ok(checkedOrderLottoResponses.lottoResponses().get(order - 1));
    }
}
