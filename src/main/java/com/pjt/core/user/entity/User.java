package com.pjt.core.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class User {

    private String userId;

    private String userPassword;

    private String email;

    private String birth;

    private String gender;

    private String phoneNumber;

    private String address;

    private LocalDateTime regDt;

    private String useYn;

    private String userLevel;

    private String profilePicture;

}
