package com.pjt.core.user.service;

import com.pjt.core.common.error.response.ErrorCode;
import com.pjt.core.user.jwt.JwtUtil;
import com.pjt.core.user.entity.User;
import com.pjt.core.user.exception.UserException;
import com.pjt.core.user.repository.UserRepository;
import com.pjt.core.user.dto.CreateUserToken;
import com.pjt.core.user.dto.LoginRequestServiceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final JwtUtil jwtUtil;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public String login(LoginRequestServiceDto request) {
		User user = userRepository.findById(request.getId())
				.orElseThrow(() -> new UserException(ErrorCode.NO_MEMBER));

		if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			throw new UserException(ErrorCode.INVALID_PASSWORD);
		}

		CreateUserToken target = new CreateUserToken();
		BeanUtils.copyProperties(user, target);

		return jwtUtil.createAccessToken(target);
	}

}
