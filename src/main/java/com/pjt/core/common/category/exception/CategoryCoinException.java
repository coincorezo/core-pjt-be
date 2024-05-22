package com.pjt.core.common.category.exception;

import com.pjt.core.common.error.response.ErrorCode;
import lombok.Getter;

@Getter
public class CategoryCoinException extends RuntimeException {

	private final ErrorCode errorCode;

	public CategoryCoinException(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}

}
