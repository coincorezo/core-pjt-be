package com.pjt.core.common.code.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@Builder
@ToString
public class UpdateCommonCodeRequest {

	@Schema(description = "공통코드")
	private String commonCode;

	@Schema(description = "수정할 공통코드명")
	@NotBlank(message = "공통코드는 필수입니다.")
	private String updateCommonCode;

	@Schema(description = "공통코드명")
	@NotBlank(message = "공통코드명은 필수입니다.")
	private String commonCodeName;

	@Schema(description = "공통코드 설명")
	private String commonCodeDescription;

	@Schema(description = "참조 공통코드")
	private String ref;

	@Schema(description = "사용여부 (Y/N)")
	private String useYn;

	@Schema(description = "등록자 ID")
	private String regId;

	@Schema(description = "수정자 ID")
	private String uptId;

}
