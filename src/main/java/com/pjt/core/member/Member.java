package com.pjt.core.member;

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

    protected Member() {
    }

    @Builder
    public Member(String id, String password) {
        this.id = id;
        this.password = password;
    }

}
