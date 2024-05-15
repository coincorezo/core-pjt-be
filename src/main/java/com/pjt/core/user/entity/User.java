package com.pjt.core.user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class User {

    @Id
    private String id;

    private String password;

    private String email;

    private String name;

    private String useYn;

    private String userLevel;

    protected User() {
    }

    @Builder
    public User(String id, String password, String email) {
        this.id = id;
        this.password = password;
        this.email = email;
    }
}
