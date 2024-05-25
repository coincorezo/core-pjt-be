package com.pjt.core.user.service;

import com.pjt.core.common.error.response.ErrorCode;
import com.pjt.core.common.util.RequestUtils;
import com.pjt.core.user.dto.CreateUserToken;
import com.pjt.core.user.dto.LoginRequestServiceDto;
import com.pjt.core.user.entity.LoginHistory;
import com.pjt.core.user.entity.User;
import com.pjt.core.user.exception.UserException;
import com.pjt.core.user.jwt.JwtUtil;
import com.pjt.core.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

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
	@Transactional
	public String login(LoginRequestServiceDto request) {
		User user = userMapper.selectUserById(request.getId())
				.orElseThrow(() -> new UserException(ErrorCode.NO_MEMBER));

		if (!passwordEncoder.matches(request.getPassword(), user.getUserPassword())) {
			throw new UserException(ErrorCode.INVALID_PASSWORD);
		}

		LoginHistory loginHistory = LoginHistory.builder()
				.userId(user.getUserId())
				.loginDt(LocalDateTime.now())
				.loginIp(RequestUtils.getIp())
				.build();

		// 로그인 이력 저장
		saveLoginHistory(loginHistory);

		return jwtUtil.createAccessToken(CreateUserToken.fromEntity(user));
	}

	/**
	 * 로그인 이력 저장
	 * @param loginHistory 로그인 이력
	 */
	public void saveLoginHistory(LoginHistory loginHistory) {
		userMapper.insertLoginHistory(loginHistory);
	}

}
