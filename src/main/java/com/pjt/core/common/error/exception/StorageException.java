package com.pjt.core.common.error.exception;

import com.pjt.core.common.error.response.ErrorCode;

public class StorageException extends RuntimeException {

	public StorageException(ErrorCode errorCode) {
		super(errorCode.getMessage());
	}

}
