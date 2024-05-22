package com.pjt.core.common.code.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ReadCommonCodeResponse {

	@Schema(description = "공통코드")
	private String commonCode;

	@Schema(description = "공통코드명")
	private String commonCodeName;

	@Schema(description = "공통코드 설명")
	private String commonCodeDescription;

	@Schema(description = "참조 공통코드")
	private String ref;

	@Schema(description = "공통코드 계층")
	private int depth;

	@Schema(description = "사용여부")
	private String useYn;

}
