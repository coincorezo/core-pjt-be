package com.pjt.core.common.storage.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Storage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("이미지번호")
    private Long imgId;

    @Comment("이미지원본이름")
    private String imgOriginNm;

    @Comment("이미지저장이름")
    private String imgSaveNm;

    @Comment("이미지확장자")
    private String imgExt;

    @Comment("파일사이즈")
    private Long imgFileSize;

    @Comment("이미지물리경로")
    private String fileUrlPath;

    @CreationTimestamp
    @Comment("등록일자")
    private LocalDateTime regDt;

}
