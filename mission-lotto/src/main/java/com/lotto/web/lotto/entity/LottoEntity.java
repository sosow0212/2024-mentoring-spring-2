package com.lotto.web.lotto.entity;

import com.lotto.web.member.entity.Member;
import jakarta.persistence.*;

@Entity
@Table(name = "LottoTicket")
public class LottoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;
    @Column
    private String lottoNumber;
    @Column
    private boolean win;

    public LottoEntity(Member member, String lottoNumber, int count) {
        this.member = member;
        this.lottoNumber = lottoNumber;
        this.win = isWin(count);
    }

    protected LottoEntity() {
    }

    public String getLottoNumber() {
        return lottoNumber;
    }

    public boolean getWin() {
        return win;
    }

    private boolean isWin(int count){
        return count >= 3;
    }
}
