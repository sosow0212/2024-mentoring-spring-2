package com.board.global.exception.exceptions;

public class CustomException extends RuntimeException {

    private final CustomErrorCode customErrorCode;

    public CustomException(CustomErrorCode customErrorCode) {
        super(customErrorCode.getCustomCode() + ": " + customErrorCode.getMessage());
        this.customErrorCode = customErrorCode;
    }

    public CustomErrorCode getCustomErrorCode() {
        return customErrorCode;
    }
}
