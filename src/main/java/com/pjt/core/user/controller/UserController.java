package com.pjt.core.user.controller;

import com.pjt.core.common.ApiResponse;
import com.pjt.core.user.dto.CreateUserRequestDto;
import com.pjt.core.user.dto.LoginRequestDto;
import com.pjt.core.user.dto.LoginRequestServiceDto;
import com.pjt.core.user.service.AuthService;
import com.pjt.core.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Tag(name = "UserController", description = "사용자 API")
public class UserController {

	private final AuthService authService;
	private final UserService userService;

	/**
	 * 로그인
	 * @param request 로그인 요청
	 * @param response 응답
	 * @return 응답
	 */
	@PostMapping("/login")
	@Operation(summary = "로그인", description = "로그인을 합니다.")
	public ApiResponse<Void> login(
			@Valid @RequestBody LoginRequestDto request,
			HttpServletResponse response
	) {
		String token = authService.login(LoginRequestServiceDto.of(request.getId(), request.getPassword()));

		response.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);

		return ApiResponse.ok(null);
	}

	/**
	 * 회원가입
	 * @param request 회원가입 요청
	 * @return 응답
	 */
	@PostMapping("/signup")
	@Operation(summary = "회원가입", description = "회원가입을 합니다.")
	public ApiResponse<Void> signup(@Valid @RequestBody CreateUserRequestDto request) {
		userService.save(request);

		return ApiResponse.ok(null);
	}

}
