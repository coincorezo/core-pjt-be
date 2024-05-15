package com.pjt.core.member;

import com.pjt.core.member.dto.CustomUserInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberDetailsService implements UserDetailsService {

	private final MemberRepository memberRepository;

	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		Member member = memberRepository.findById(id)
				.orElseThrow(() -> new UsernameNotFoundException(id));

		CustomUserInfoDto target = new CustomUserInfoDto();
		BeanUtils.copyProperties(member, target);

		return new MemberDetails(target);
	}

}
