package com.lotto.domain;

public enum LottoRank {

    FIRST(6, 2000000000),
    SECOND(5, 1500000),
    THIRD(4, 50000),
    FOURTH(3, 5000),
    NONE(0, 0);

    private final int count;
    private final int prize;

    LottoRank(int count, int prize) {
        this.count = count;
        this.prize = prize;
    }

    public int getCount() {
        return count;
    }

    public int getPrize() {
        return prize;
    }

    public static LottoRank getRank(int count) {
        for (LottoRank rank : values()) {
            if (rank.getCount() == count) {
                return rank;
            }
        }
        return NONE;
    }

    public boolean getRankResult(LottoRank lottoRank) {
        if (lottoRank == NONE) {
            return false;
        }
        return true;
    }
}
