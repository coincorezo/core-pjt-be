package com.pjt.core.login.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "사용자 정보 ")
public class UserReqDto {

	@Schema(description = "사용자 ID")
	@NotBlank(message = "사용자 ID는 필수입니다.")
	private String userId;

	@Schema(description = "사용자 PWD")
	@NotBlank(message = "사용자 패스워드는 필수입니다.")
	private String userPassword;

	private String passwordCheck;

	@Schema(description = "사용자 명")
	@NotBlank(message = "사용자 사용자 명 필수입니다.")
	private String userName;

	@Schema(description = "사용자 email")
	// @NotBlank(message = "사용자 이메일 필수입니다.")
	private String email;

	@Schema(description = "생년월일")
	private String birth;

	@Schema(description = "성별")
	private String gender;

	@Schema(description = "연락처")
	private String phoneNumber;

	@Schema(description = "주소")
	private String address;

	@Schema(description = "가입일자")
	private String regDt;

	@Schema(description = "사용여부")
	private String useYn;

	@Schema(description = "사용자 레벨")
	private String userLevel;

	@Schema(description = "사용자 프로필")
	private String profile_picture;

}
