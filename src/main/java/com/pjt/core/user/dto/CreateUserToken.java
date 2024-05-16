package com.pjt.core.user.dto;

import com.pjt.core.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class CreateUserToken {

	private String id;

	private String password;

	private String email;

	private String userLevel;

	public static CreateUserToken fromEntity(User user) {
		return CreateUserToken.builder()
				.id(user.getUserId())
				.password(user.getUserPassword())
				.email(user.getEmail())
				.userLevel(user.getUserLevel())
				.build();
	}

}
