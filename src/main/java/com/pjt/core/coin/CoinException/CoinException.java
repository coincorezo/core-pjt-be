package com.pjt.core.coin.CoinException;

import com.pjt.core.common.error.response.ErrorCode;
import lombok.Getter;

@Getter
public class CoinException extends RuntimeException {
    private final ErrorCode errorCode;


    public CoinException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
