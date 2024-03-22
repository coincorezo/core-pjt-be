package com.pjt.core.common.error.exception;

import com.pjt.core.common.error.response.ErrorCode;

public class NoDataException extends RuntimeException {

	public NoDataException(ErrorCode errorCode) {
		super(errorCode.getMessage());
	}

}
