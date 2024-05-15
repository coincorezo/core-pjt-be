package com.pjt.core.user.dto;

import com.pjt.core.user.entity.User;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class CurrentUser {

	private String id;

	private String email;

	private String useYn;

	private String userLevel;

	@Builder
	private CurrentUser(String id, String email, String useYn, String userLevel) {
		this.id = id;
		this.email = email;
		this.useYn = useYn;
		this.userLevel = userLevel;
	}

	public static CurrentUser fromEntity(User user) {
		return CurrentUser.builder()
				.id(user.getUserId())
				.email(user.getEmail())
				.useYn(user.getUseYn())
				.userLevel(user.getUserLevel())
				.build();
	}

}
