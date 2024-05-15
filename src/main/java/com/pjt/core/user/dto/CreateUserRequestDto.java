package com.pjt.core.user.dto;

import com.pjt.core.user.entity.User;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@AllArgsConstructor
@Builder
public class CreateUserRequestDto {

    @NotBlank(message = "아이디를 입력해주세요.")
    private String id;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;

    @NotBlank(message = "생년월일을 입력해주세요")
    private String birth;

    @NotBlank(message = "성별을 입력해주세요")
    private String gender;

    @NotBlank(message = "전화번호를 입력해주세요")
    private String phoneNumber;

    @NotBlank(message = "주소를 입력해주세요")
    private String address;

    public void setEncodedPassword(PasswordEncoder passwordEncoder, String password) {
        this.password = passwordEncoder.encode(password);
    }

    public static User toEntity(CreateUserRequestDto request) {
        return User.builder()
                .userId(request.getId())
                .userPassword(request.getPassword())
                .email(request.getEmail())
                .birth(request.getBirth())
                .phoneNumber(request.getPhoneNumber())
                .address(request.getAddress())
                .build();
    }

}
