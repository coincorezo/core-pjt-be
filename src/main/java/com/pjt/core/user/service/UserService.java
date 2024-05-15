package com.pjt.core.user.service;

import com.pjt.core.common.error.response.ErrorCode;
import com.pjt.core.common.util.RequestUtils;
import com.pjt.core.user.dto.CurrentUser;
import com.pjt.core.user.entity.User;
import com.pjt.core.user.exception.UserException;
import com.pjt.core.user.jwt.JwtUtil;
import com.pjt.core.user.repository.UserRepository;
import com.pjt.core.user.dto.CreateUserRequest;
import com.pjt.core.user.dto.CreateUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    public CreateUserResponse save(CreateUserRequest request) {
        // 이미 존재하는 회원인지 확인
        checkExistMember(request.getId());

        // 비밀번호 암호화
        request.setEncodedPassword(passwordEncoder, request.getPassword());

        // 회원 저장
        User savedUser = userRepository.save(CreateUserRequest.toEntity(request));

        return CreateUserResponse.fromEntity(savedUser);
    }

    /**
     * 이미 존재하는 회원인지 확인
     * @param id 회원 아이디
     */
    @Transactional(readOnly = true)
    public void checkExistMember(String id) {
		userRepository.findById(id)
                .ifPresent(m -> {
                    throw new UserException(ErrorCode.EXIST_MEMBER);
                });
    }

    /**
     * 로그인한 사용자 정보 조회
     * @return 로그인한 사용자 정보
     */
    @Transactional(readOnly = true)
    public CurrentUser getLoginUser() {
        String accessToken = jwtUtil.getAccessToken(RequestUtils.getHttpServletRequest());
        String userId = jwtUtil.getUserId(accessToken);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(ErrorCode.NO_MEMBER));

        return CurrentUser.fromEntity(user);
    }

}
