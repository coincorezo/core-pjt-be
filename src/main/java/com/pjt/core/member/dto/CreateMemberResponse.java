package com.pjt.core.member.dto;

import com.pjt.core.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class CreateMemberResponse {

    private String id;

    public static CreateMemberResponse fromEntity(Member member) {
        return CreateMemberResponse.builder()
                .id(member.getId())
                .build();
    }

}
