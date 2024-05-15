package com.pjt.core.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginRequestDto {

	@NotBlank(message = "아이디를 입력해주세요.")
	private String id;

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
