package com.pjt.core.user.service;

import com.pjt.core.common.error.response.ErrorCode;
import com.pjt.core.user.dto.CreateUserToken;
import com.pjt.core.user.dto.LoginRequestServiceDto;
import com.pjt.core.user.entity.User;
import com.pjt.core.user.exception.UserException;
import com.pjt.core.user.jwt.JwtUtil;
import com.pjt.core.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final JwtUtil jwtUtil;
	private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder;

	/**
	 * 로그인
	 * @param request 로그인 요청
	 * @return Access Token
	 */
	public String login(LoginRequestServiceDto request) {
		User user = userMapper.selectUserById(request.getId())
				.orElseThrow(() -> new UserException(ErrorCode.NO_MEMBER));

		if (!passwordEncoder.matches(request.getPassword(), user.getUserPassword())) {
			throw new UserException(ErrorCode.INVALID_PASSWORD);
		}

		return jwtUtil.createAccessToken(CreateUserToken.fromEntity(user));
	}

}
