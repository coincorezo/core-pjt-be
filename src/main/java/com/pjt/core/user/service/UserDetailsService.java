package com.pjt.core.user.service;

import com.pjt.core.user.entity.User;
import com.pjt.core.user.entity.UserDetails;
import com.pjt.core.user.repository.UserRepository;
import com.pjt.core.user.dto.CreateUserToken;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UsernameNotFoundException(id));

		CreateUserToken target = new CreateUserToken();
		BeanUtils.copyProperties(user, target);

		return new UserDetails(target);
	}

}
