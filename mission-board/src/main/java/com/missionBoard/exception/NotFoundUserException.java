package com.missionBoard.exception;

public class NotFoundUserException extends RuntimeException {
    public NotFoundUserException(){
        super("사용자를 찾을 수 없습니다.");
    }
}
