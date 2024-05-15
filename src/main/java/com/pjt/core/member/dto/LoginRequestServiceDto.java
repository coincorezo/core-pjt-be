package com.pjt.core.member.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginRequestServiceDto {

	private String id;

	private String password;

	@Builder
	public LoginRequestServiceDto(String id, String password) {
		this.id = id;
		this.password = password;
	}

	public static LoginRequestServiceDto of(String id, String password) {
		return LoginRequestServiceDto.builder()
				.id(id)
				.password(password)
				.build();
	}

}
