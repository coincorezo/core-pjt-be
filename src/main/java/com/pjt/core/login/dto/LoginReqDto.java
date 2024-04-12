package com.pjt.core.login.dto;

import com.pjt.core.common.util.Schema;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginReqDto {
	@Schema(description = "사용자 ID")
	@NotBlank(message = "사용자 ID는 필수입니다.")
	private String userId;

	@Schema(description = "사용자 PWD")
	@NotBlank(message = "사용자 패스워드는 필수입니다.")
	private String userPassword;

}
