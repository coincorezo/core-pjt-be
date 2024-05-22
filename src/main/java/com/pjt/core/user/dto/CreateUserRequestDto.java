package com.pjt.core.user.dto;

import com.pjt.core.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class CreateUserRequestDto {

    @Schema(description = "아이디", example = "test")
    @NotBlank(message = "아이디를 입력해주세요.")
    private String id;

    @Schema(description = "비밀번호", example = "1234")
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    @Schema(description = "이메일", example = "test123@gmail.com")
    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;

    @Schema(description = "생년월일", example = "19990101")
    @NotBlank(message = "생년월일을 입력해주세요")
    private String birth;

    @Schema(description = "성별", example = "F")
    @NotBlank(message = "성별을 입력해주세요")
    private String gender;

    @Schema(description = "전화번호", example = "01012345678")
    @NotBlank(message = "전화번호를 입력해주세요")
    private String phoneNumber;

    @Schema(description = "주소", example = "서울시 강남구")
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
