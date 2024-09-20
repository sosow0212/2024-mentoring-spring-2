package com.lotto.entity;

import com.lotto.domain.LottoRank;
import com.lotto.domain.LottoResult;
import com.lotto.exception.InsufficientFundsException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;


@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
@Table(name = "lotto_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "NAME")
    private String userName;

    @Column
    private int money;

    @OneToMany(mappedBy = "user")
    private List<UserLotto> userLotto = new ArrayList<>();

    private static final int TICKET_PRICE = 1000;

    public void addUserLotto(UserLotto userLotto) {
        if (this.userLotto == null) {
            this.userLotto = new ArrayList<>();
        }
        userLotto.setUser(this);
        this.userLotto.add(userLotto);
    }

    public void validateMoney(int ticketCount) {
        int price = ticketCount * TICKET_PRICE;
        if (this.money < price) {
            throw new InsufficientFundsException();
        }
    }

    public int calculateTotalPrize(List<Integer> winNumbers) {
        int prizeMoney = 0;
        for (UserLotto userLotto : this.userLotto) {
            LottoRank lottoRank = new LottoResult(userLotto.getLottoNumbers(), winNumbers).getLottoRank();
            prizeMoney += lottoRank.getPrize();
        }
        return prizeMoney;
    }

    public void subtractMoney(int ticketCount) {
        int price = ticketCount * 1000;
        this.money -= price;
    }
}
