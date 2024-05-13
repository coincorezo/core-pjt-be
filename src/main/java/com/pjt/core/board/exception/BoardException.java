package com.pjt.core.board.exception;

import com.pjt.core.common.error.response.ErrorCode;
import lombok.Getter;

@Getter
public class BoardException extends RuntimeException {

    private final ErrorCode errorCode;

    public BoardException( ErrorCode errorCode) {
        this.errorCode = errorCode;

    }
}
