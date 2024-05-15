package com.pjt.core.user.exception;

import com.pjt.core.common.error.response.ErrorCode;
import lombok.Getter;

@Getter
public class UserException extends RuntimeException {

	private final ErrorCode errorCode;

	public UserException(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}

}
