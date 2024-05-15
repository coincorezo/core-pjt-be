package com.pjt.core.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserToken {

	private String id;

	private String password;

	private String email;

	private String name;

	private String userLevel;

	public CreateUserToken() {
	}

	@Builder
	public CreateUserToken(String id, String password, String email, String name, String userLevel) {
		this.id = id;
		this.password = password;
		this.email = email;
		this.name = name;
		this.userLevel = userLevel;
	}

}
