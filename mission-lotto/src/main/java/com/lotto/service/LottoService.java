package com.lotto.service;

import com.lotto.domain.Lotto;
import com.lotto.domain.LottoJudge;
import com.lotto.domain.Lottos;
import com.lotto.domain.RandomNumber;
import com.lotto.entity.UserLotto;
import com.lotto.exception.NotExistLottoException;
import com.lotto.exception.NotExistUserNameException;
import com.lotto.repository.LottoRepository;
import com.lotto.repository.UserRepository;
import com.lotto.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LottoService {

    private UserRepository userRepository;
    private LottoRepository lottoRepository;
    private RandomNumber randomNumber;

    @Autowired
    public LottoService(UserRepository userRepository, LottoRepository lottoRepository, RandomNumber randomNumber) {
        this.lottoRepository = lottoRepository;
        this.userRepository = userRepository;
        this.randomNumber = randomNumber;
    }

    public void buyLotto(String name, int ticketCount) {
        User user = userRepository.findByUserName(name)
                .orElseThrow(NotExistUserNameException::new);
        user.validateMoney(ticketCount);
        Lottos lottos = new Lottos(new ArrayList<>());
        lottos = lottos.buyLotto(ticketCount, randomNumber);

        for (Lotto lotto : lottos.getLottos()) {
            UserLotto userLotto = UserLotto.create(lotto.getLottoNumbers(), user);
            user.addUserLotto(userLotto);
            lottoRepository.save(userLotto);
        }

        user.subtractMoney(ticketCount);
        userRepository.save(user);
    }

    public List<UserLotto> getUserLotto(String name) {
        return lottoRepository.findByUser_UserName(name)
                .orElseThrow(NotExistUserNameException::new);
    }

    public UserLotto getTargetLottoResult(Long lottoId) {
        return lottoRepository.findByLottoId(lottoId)
                .orElseThrow(NotExistLottoException::new);
    }

    public int getUserLottoCount(User user) {
        return user.getUserLotto().size();
    }

    public int calculateUserPrize(User user, List<Integer> winNumbers) {
        return user.calculateTotalPrize(winNumbers);
    }

    public List<Lotto> getLottoResults(String name, List<Integer> winNumbers) {
        List<UserLotto> userLottos = getUserLotto(name);
        Lottos lottos = new Lottos(userLottos.stream()
                .map(userLotto -> Lotto.of(userLotto.getLottoNumbers()))
                .toList());
        return lottos.getLottos();
    }

    public LottoJudge createLottoJudge(UserLotto userLotto, List<Integer> winNumbers) {
        return new LottoJudge(userLotto, winNumbers);
    }
}
