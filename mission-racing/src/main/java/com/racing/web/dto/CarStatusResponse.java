package com.racing.web.dto;

public class CarStatusResponse {

    private final String name;
    private final int count;

    public CarStatusResponse(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public static CarStatusResponse from(String carName, int moveCount) {
        return new CarStatusResponse(carName, moveCount);
    }

    public String getCarName(){
        return name;
    }

    public int getMoveCount(){
        return count;
    }
}
