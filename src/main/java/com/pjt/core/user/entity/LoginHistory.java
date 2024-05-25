package com.pjt.core.user.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class LoginHistory {

	private String userId;

	private LocalDateTime loginDt;

	private String loginIp;

}
