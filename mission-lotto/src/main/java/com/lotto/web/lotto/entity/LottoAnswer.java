package com.lotto.web.lotto.entity;

import jakarta.persistence.*;

@Entity
public class LottoAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String lottoAnswer;

    public LottoAnswer(String lottoAnswer) {
        this.lottoAnswer = lottoAnswer;
    }

    protected LottoAnswer() {
    }

    public String getLottoAnswer() {
        return lottoAnswer;
    }
}
