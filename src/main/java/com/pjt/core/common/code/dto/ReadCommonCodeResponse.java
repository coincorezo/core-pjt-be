package com.pjt.core.common.code.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ReadCommonCodeResponse {

	private String commonCode;

	private String commonCodeName;

	private String commonCodeDescription;

	private String ref;

	private int depth;

	private String useYn;

}
