package com.pjt.core.common.error.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
	NO_DATA("common", "데이터가 존재하지 않습니다."),
	INVALID_INPUT_VALUE("common", "유효하지 않는 입력 값입니다."),
	INTERNAL_SERVER_ERROR("common", "서버 에러가 발생하였습니다.")
	;

	private final String code;
	private final String message;
}
