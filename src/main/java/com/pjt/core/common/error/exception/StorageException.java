package com.pjt.core.common.error.exception;

import com.pjt.core.common.error.response.ErrorCode;
import lombok.Getter;

@Getter
public class StorageException extends RuntimeException {

	private final ErrorCode errorCode;

	public StorageException(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}

}
