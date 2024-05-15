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

	@Override
	public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		User user = userMapper.selectUserById(id)
				.orElseThrow(() -> new UsernameNotFoundException(id));

		return new UserDetails(CreateUserToken.fromEntity(user));
	}

}
