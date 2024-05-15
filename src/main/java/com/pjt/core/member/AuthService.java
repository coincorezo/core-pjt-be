package com.pjt.core.member;

import com.pjt.core.common.error.response.ErrorCode;
import com.pjt.core.member.dto.CustomUserInfoDto;
import com.pjt.core.member.dto.LoginRequestServiceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final JwtUtil jwtUtil;
	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;

	public String login(LoginRequestServiceDto request) {
		Member member = memberRepository.findById(request.getId())
				.orElseThrow(() -> new MemberException(ErrorCode.NO_MEMBER));

		if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
			throw new MemberException(ErrorCode.INVALID_PASSWORD);
		}

		CustomUserInfoDto target = new CustomUserInfoDto();
		BeanUtils.copyProperties(member, target);

		return jwtUtil.createAccessToken(target);
	}

}
