package com.lotto.controller;

import com.lotto.dto.response.AllResultsResponse;
import com.lotto.dto.request.RegisterRequest;
import com.lotto.dto.request.UserRequest;
import com.lotto.dto.response.UserResultResponse;
import com.lotto.dto.response.UserResponse;
import com.lotto.domain.LottoJudge;
import com.lotto.entity.User;
import com.lotto.entity.UserLotto;
import com.lotto.service.LottoService;
import com.lotto.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/lotto")
@RestController
public class LottoController {

    private final LottoService lottoService;
    private final UserService userService;

    public LottoController(LottoService lottoService, UserService userService) {
        this.lottoService = lottoService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Void> registerUser(@RequestBody RegisterRequest registerRequest) {
        userService.registerUser(registerRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/buy")
    public ResponseEntity<Void> buyLotto(@RequestBody UserRequest userRequest) {
        lottoService.buyLotto(userRequest.name(), userRequest.ticketCount());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/result")
    public ResponseEntity<List<UserResultResponse>> getLottoResult(@RequestParam(name = "name") String name, @RequestParam(name = "winNumbers") List<Integer> winNumbers) {
        List<UserResultResponse> resultResponses = UserResultResponse.fromLottos(lottoService.getLottoResults(name, winNumbers), winNumbers);
        return ResponseEntity.ok(resultResponses);
    }

    @GetMapping("/result/{lottoId}")
    public ResponseEntity<UserResultResponse> getTargetLottoResult(@PathVariable(name = "lottoId") Long lottoId, @RequestParam(name = "winNumbers") List<Integer> winNumbers) {
        UserLotto userLotto = lottoService.getTargetLottoResult(lottoId);
        LottoJudge judge = lottoService.createLottoJudge(userLotto, winNumbers);
        boolean isWinning = judge.isWinningLotto();
        UserResultResponse response = new UserResultResponse(judge.getLottoNumbers(), isWinning);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/result/all")
    public ResponseEntity<AllResultsResponse> getAllResults(@RequestParam(name = "winNumbers") List<Integer> winNumbers) {
        List<User> users = userService.getAllUsers();
        List<UserResponse> userResults = users.stream()
                .map(user -> new UserResponse(user.getUserName(), lottoService.getUserLottoCount(user), lottoService.calculateUserPrize(user, winNumbers)))
                .toList();
        return ResponseEntity.ok(new AllResultsResponse(userResults));
    }
}
