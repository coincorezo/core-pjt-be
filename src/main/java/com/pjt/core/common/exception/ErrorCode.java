package com.pjt.core.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
	INVALID_INPUT_VALUE("common", "유효하지 않는 입력 값입니다."),
	INTERNAL_SERVER_ERROR("common", "서버 에러가 발생하였습니다.")
	;

	private final String code;
	private final String message;
}
