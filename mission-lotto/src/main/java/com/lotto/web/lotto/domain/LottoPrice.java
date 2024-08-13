package com.lotto.web.lotto.domain;

public enum LottoPrice {

    THREE(3, 5000),
    FOUR(4, 50000),
    FIVE(5, 1500000),
    SIX(6, 2000000000);

    private static final int INITIAL_NUMBER = 0;
    private static final int LAST_PLACE = 3;

    private final int price;

    LottoPrice(int count, int price) {
        this.price = price;
    }

    public static int getLottoPrice(int count) {
        if(count < LAST_PLACE)
        {
            return INITIAL_NUMBER;
        }
        LottoPrice[] Prices = LottoPrice.values();
        return Prices[count - LAST_PLACE].getPrice();
    }

    private int getPrice(){
        return price;
    }
}
