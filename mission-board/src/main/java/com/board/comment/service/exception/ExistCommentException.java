package com.board.comment.service.exception;

import com.board.global.exception.exceptions.CustomErrorCode;
import com.board.global.exception.exceptions.CustomException;

public class ExistCommentException extends CustomException {
    public ExistCommentException() {
        super(CustomErrorCode.DO_NOT_HAVE_RIGHT_COMMENT);
    }
}
