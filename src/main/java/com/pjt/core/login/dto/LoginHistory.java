package com.pjt.core.login.dto;

import com.pjt.core.common.util.Schema;

import lombok.Data;

@Data
public class LoginHistory {

	@Schema(description = "사용자 ID")
	private String userId;

	@Schema(description = "로그인시간")
	private String loginDt;

	@Schema(description = "사용자 ip주소")
	private String loginIp;

}
