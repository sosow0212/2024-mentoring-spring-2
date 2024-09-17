package com.missionBoard.exception;

public class InvalidJwtTokenException extends RuntimeException{
    public InvalidJwtTokenException(){
        super("토큰이 올바르지 않습니다.");
    }
}
