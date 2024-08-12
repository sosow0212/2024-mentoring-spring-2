package com.lotto.web.global.exception.exceptions;

public enum CustomErrorCode {

    NOT_LOTTO_RANGE_EXCEPTION(400, "로또 범위 아님."),
    USER_NOT_FOUND_EXCEPTION(404, "유저 없음."),
    MONEY_NOT_FOUNT_EXCEPTION(404, "돈 없음."),
    LOTTO_NOT_FOUND_EXCEPTION(404, "로또 없음.");

    private final int status;
    private final String message;

    CustomErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }
}
