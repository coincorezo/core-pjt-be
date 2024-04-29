package com.pjt.core.common.code.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class CreateCommonCodeRequest {

	@NotBlank(message = "공통코드는 필수입니다.")
	private String commonCode;

	@NotBlank(message = "공통코드명은 필수입니다.")
	private String commonCodeName;

	private String commonCodeDescription;

	private String useYn;

	private String regId;

	private String uptId;

}
