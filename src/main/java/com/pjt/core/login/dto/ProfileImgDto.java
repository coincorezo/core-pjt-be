package com.pjt.core.login.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "사용자 프로필 정보 ")
public class ProfileImgDto {

	@Schema(description = "사용자 ID")
	@NotBlank(message = "사용자 ID는 필수입니다.")
	private String userId;

	@Schema(description = "프로필 이미지 번호")
	private String imgNo;

	@Schema(description = "프로필 이미지 이름")
	private String imgNm;

	@Schema(description = "이미지 확장자")
	private String imgExtNm;

	@Schema(description = "이미지 사이즈")
	private String imgFileSize;

	@Schema(description = "이미지 물리경로")
	private String fileUrlPath;

}
