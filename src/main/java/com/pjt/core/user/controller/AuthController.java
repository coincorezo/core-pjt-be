package com.pjt.core.user.controller;

import com.pjt.core.user.dto.CreateUserRequestDto;
import com.pjt.core.user.dto.LoginRequestDto;
import com.pjt.core.user.dto.LoginRequestServiceDto;
import com.pjt.core.user.service.AuthService;
import com.pjt.core.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

	private final AuthService authService;
	private final UserService userService;

	@PostMapping("/login")
	public ResponseEntity<?> login(
			@Valid @RequestBody LoginRequestDto request,
			HttpServletResponse response
	) {
		String token = authService.login(LoginRequestServiceDto.of(request.getId(), request.getPassword()));

		response.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/signup")
	public ResponseEntity<Void> signup(@Valid @RequestBody CreateUserRequestDto request) {
		userService.save(request);

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}
