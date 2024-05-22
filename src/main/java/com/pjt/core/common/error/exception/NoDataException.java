package com.pjt.core.common.error.exception;

import com.pjt.core.common.error.response.ErrorCode;

public class NoDataException extends RuntimeException {

	public NoDataException() {
		super(ErrorCode.NO_DATA.getMessage());
	}

	public NoDataException(ErrorCode errorCode) {
		super(errorCode.getMessage());
	}

}
