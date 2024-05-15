package com.pjt.core.user.dto;

import com.pjt.core.user.entity.Member;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class CurrentUser {

	private String id;

	private String email;

	private String name;

	private String useYn;

	private String userLevel;

	@Builder
	private CurrentUser(String id, String email, String name, String useYn, String userLevel) {
		this.id = id;
		this.email = email;
		this.name = name;
		this.useYn = useYn;
		this.userLevel = userLevel;
	}

	public static CurrentUser fromEntity(Member member) {
		return CurrentUser.builder()
				.id(member.getId())
				.email(member.getEmail())
				.name(member.getName())
				.useYn(member.getUseYn())
				.userLevel(member.getUserLevel())
				.build();
	}

}
