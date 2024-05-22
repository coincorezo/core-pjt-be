package com.pjt.core.user.service;

import com.pjt.core.user.dto.CreateUserToken;
import com.pjt.core.user.entity.User;
import com.pjt.core.user.entity.UserDetails;
import com.pjt.core.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	private final UserMapper userMapper;

	/**
	 * 사용자 조회
	 * @param id 사용자 아이디
	 * @return 사용자 정보
	 * @throws UsernameNotFoundException 사용자 없음
	 */
	@Override
	public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		User user = userMapper.selectUserById(id)
				.orElseThrow(() -> new UsernameNotFoundException(id));

		return new UserDetails(CreateUserToken.fromEntity(user));
	}

}
