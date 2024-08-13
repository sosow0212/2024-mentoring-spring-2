package com.lotto.web.member.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class MemberLotto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;
    @Column
    private int lottoCount;
    @Getter
    @Column
    private int winningPrice;

    public MemberLotto(Member member) {
        this.member = member;
    }

    protected MemberLotto() {
    }

    public void updateLottoCount(int lottoCount){
        this.lottoCount += lottoCount;
    }

    public void updateWinningPrice(int winningPrice){
        this.winningPrice  = winningPrice;
    }
}
