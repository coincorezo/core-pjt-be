package com.pjt.core.login;

import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pjt.core.login.dto.LoginReqDto;
import com.pjt.core.login.dto.UserResDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("api")
public class LoginController {

	@Autowired
	LoginService loginService;

	@PostMapping("/login")
	// @Operation(description ="사용자 login")
	public UserResDto login(@Validated LoginReqDto loginReqDto, HttpServletRequest request)
			throws UnknownHostException {

		return loginService.login(loginReqDto, request);
	}

	@PostMapping("/logout")
	// @Operation(description ="사용자 login")
	public void logout(@Validated LoginReqDto loginReqDto, HttpServletRequest request) throws UnknownHostException {
		HttpSession session = request.getSession(false);
		session.invalidate(); // 세션 초기화

	}

}
