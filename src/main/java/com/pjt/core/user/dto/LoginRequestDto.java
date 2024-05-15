package com.pjt.core.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginRequestDto {

	@Schema(description = "아이디", example = "test")
	@NotBlank(message = "아이디를 입력해주세요.")
	private String id;

	@Schema(description = "비밀번호", example = "1234")
	@NotBlank(message = "비밀번호를 입력해주세요.")
	private String password;

	private LoginRequestDto() {
	}

	@Builder
	public LoginRequestDto(String id, String password) {
		this.id = id;
		this.password = password;
	}

}
