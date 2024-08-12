package com.lotto;

import com.lotto.dto.request.RegisterRequest;
import com.lotto.domain.LottoJudge;
import com.lotto.entity.User;
import com.lotto.entity.UserLotto;
import com.lotto.exception.InsufficientFundsException;
import com.lotto.exception.NotExistLottoException;
import com.lotto.exception.NotExistUserNameException;
import com.lotto.repository.LottoRepository;
import com.lotto.repository.UserRepository;
import com.lotto.service.LottoService;
import com.lotto.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

@Transactional
@SpringBootTest
public class LottoServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LottoRepository lottoRepository;

    @Autowired
    private LottoService lottoService;

    @Autowired
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        lottoRepository.deleteAll();
    }

    @Test
    void 금액이_부족할떄_예외_발생() {
        RegisterRequest registerRequest = new RegisterRequest("user1", 3000);
        userService.registerUser(registerRequest);

        int ticketCount = 5;

        assertThrows(InsufficientFundsException.class, () -> lottoService.buyLotto("user1", ticketCount));
    }

    @Test
    void 사용자_로또_조회_테스트() {
        RegisterRequest registerRequest = new RegisterRequest("user1", 3000);
        userService.registerUser(registerRequest);
        int ticketCount = 2;
        lottoService.buyLotto("user1", ticketCount);

        List<UserLotto> userLottos = lottoService.getUserLotto("user1");

        Assertions.assertEquals(ticketCount, userLottos.size());
    }

    @Test
    void 사용자가_존재하지_않을때_예외_발생() {
        RegisterRequest registerRequest = new RegisterRequest("user1", 3000);
        userService.registerUser(registerRequest);
        int ticketCount = 1;

        assertThrows(NotExistUserNameException.class, () -> lottoService.buyLotto("user2", ticketCount));
    }

    @Test
    void 당첨여부_조회_테스트() {
        RegisterRequest registerRequest = new RegisterRequest("user1", 3000);
        userService.registerUser(registerRequest);
        User user = userRepository.findByUserName("user1")
                .orElseThrow(NotExistLottoException::new);
        ;
        List<Integer> winNumbers = List.of(1, 2, 3, 4, 5, 6);

        UserLotto userLotto = UserLotto.create(winNumbers, user);
        lottoRepository.save(userLotto);
        LottoJudge lottoJudge = new LottoJudge(userLotto, winNumbers);
        boolean isWinning = lottoJudge.isWinningLotto();

        Assertions.assertTrue(isWinning);
    }
}
