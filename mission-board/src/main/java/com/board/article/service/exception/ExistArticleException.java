package com.board.article.service.exception;

import com.board.global.exception.exceptions.CustomErrorCode;
import com.board.global.exception.exceptions.CustomException;

public class ExistArticleException extends CustomException {
    public ExistArticleException() {
        super(CustomErrorCode.NOT_EXIST_ARTICLE);
    }
}
