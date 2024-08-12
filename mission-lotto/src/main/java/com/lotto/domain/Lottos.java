package com.lotto.domain;


import java.util.ArrayList;
import java.util.List;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public Lottos buyLotto(int numberOfTickets, RandomNumber randomNumber) {
        List<Lotto> lottos = new ArrayList<>();
        for (int count = 0; count < numberOfTickets; count++) {
            lottos.add(Lotto.createLotto(randomNumber));
        }
        return new Lottos(lottos);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
