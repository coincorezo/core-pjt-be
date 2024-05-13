package com.pjt.core.member;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(id));

        System.out.println("member = " + member);

        return new MemberDetails(member);
    }

    @Transactional
    public CreateMemberResponse save(CreateMemberRequest request) {
        request.setEncodedPassword(passwordEncoder, request.getPassword());

        Member savedMember = memberRepository.save(CreateMemberRequest.toEntity(request));

        System.out.println("savedMember = " + savedMember);

        return CreateMemberResponse.fromEntity(savedMember);
    }

}
