package com.pjt.core.user.dto;

import com.pjt.core.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class CreateUserResponse {

    private String id;

    public static CreateUserResponse fromEntity(User user) {
        return CreateUserResponse.builder()
                .id(user.getId())
                .build();
    }

}
