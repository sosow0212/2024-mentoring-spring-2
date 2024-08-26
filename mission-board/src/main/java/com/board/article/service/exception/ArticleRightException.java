package com.board.article.service.exception;

import com.board.global.exception.exceptions.CustomErrorCode;
import com.board.global.exception.exceptions.CustomException;

public class ArticleRightException extends CustomException {
    public ArticleRightException() {
        super(CustomErrorCode.DO_NOT_HAVE_RIGHT_ARTICLE);
    }
}
