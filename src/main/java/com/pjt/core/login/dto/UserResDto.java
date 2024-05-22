package com.pjt.core.login.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "사용자 정보 ")
public class UserResDto {

	@Schema(description = "사용자 ID")
	private String userId;

	@Schema(description = "사용자 PWD")
	private String userPassword;

	private String passwordCheck;

	@Schema(description = "사용자 명")
	private String userName;

	@Schema(description = "사용자 email")
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
