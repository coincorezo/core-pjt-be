package com.pjt.core.member;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@AllArgsConstructor
@Builder
public class CreateMemberRequest {

    @NotBlank(message = "아이디를 입력해주세요.")
    private String id;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    public void setEncodedPassword(PasswordEncoder passwordEncoder, String password) {
        this.password = passwordEncoder.encode(password);
    }

    public static Member toEntity(CreateMemberRequest request) {
        return Member.builder()
                .id(request.getId())
                .password(request.getPassword())
                .build();
    }

}
