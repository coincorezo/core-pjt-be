package com.pjt.core.member.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class Member {

    @Id
    private String id;

    private String password;

    private String email;

    private String name;

    private String useYn;

    private String userLevel;

    protected Member() {
    }

    @Builder
    public Member(String id, String password, String email) {
        this.id = id;
        this.password = password;
        this.email = email;
    }
}
