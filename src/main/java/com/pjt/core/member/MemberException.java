package com.pjt.core.member;

import com.pjt.core.common.error.response.ErrorCode;
import lombok.Getter;

@Getter
public class MemberException extends RuntimeException {

	private final ErrorCode errorCode;

	public MemberException(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}

}
