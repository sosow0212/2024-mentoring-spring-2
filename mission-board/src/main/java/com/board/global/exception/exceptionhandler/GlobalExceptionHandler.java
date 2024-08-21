package com.board.global.exception.exceptionhandler;

import com.board.global.exception.exceptionhandler.dto.ErrorResponse;
import com.board.global.exception.exceptions.CustomErrorCode;
import com.board.global.exception.exceptions.CustomException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleException(CustomException e) {
        CustomErrorCode customErrorCode = e.getCustomErrorCode();
        ErrorResponse errorResponse = new ErrorResponse(customErrorCode.getCustomCode(), customErrorCode.getMessage());
        return ResponseEntity.status(customErrorCode.getHttpStatus()).body(errorResponse);
    }
}
