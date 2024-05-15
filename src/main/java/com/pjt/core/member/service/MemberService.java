package com.pjt.core.member.service;

import com.pjt.core.common.error.response.ErrorCode;
import com.pjt.core.member.entity.Member;
import com.pjt.core.member.exception.MemberException;
import com.pjt.core.member.repository.MemberRepository;
import com.pjt.core.member.dto.CreateMemberRequest;
import com.pjt.core.member.dto.CreateMemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public CreateMemberResponse save(CreateMemberRequest request) {
        // 이미 존재하는 회원인지 확인
        checkExistMember(request.getId());

        // 비밀번호 암호화
        request.setEncodedPassword(passwordEncoder, request.getPassword());

        // 회원 저장
        Member savedMember = memberRepository.save(CreateMemberRequest.toEntity(request));

        return CreateMemberResponse.fromEntity(savedMember);
    }

    /**
     * 이미 존재하는 회원인지 확인
     * @param id 회원 아이디
     */
    @Transactional(readOnly = true)
    public void checkExistMember(String id) {
		memberRepository.findById(id)
                .ifPresent(m -> {
                    throw new MemberException(ErrorCode.EXIST_MEMBER);
                });
    }

}
