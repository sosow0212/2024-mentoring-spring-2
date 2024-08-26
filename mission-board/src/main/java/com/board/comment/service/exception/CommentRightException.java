package com.board.comment.service.exception;

import com.board.global.exception.exceptions.CustomErrorCode;
import com.board.global.exception.exceptions.CustomException;

public class CommentRightException extends CustomException {
    public CommentRightException() {
        super(CustomErrorCode.NOT_EXIST_COMMENT);
    }
}
