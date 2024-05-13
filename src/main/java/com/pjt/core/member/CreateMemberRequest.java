package com.pjt.core.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@AllArgsConstructor
@Builder
public class CreateMemberRequest {

    private String id;

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
