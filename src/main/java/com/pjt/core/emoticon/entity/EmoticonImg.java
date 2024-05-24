package com.pjt.core.emoticon.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class EmoticonImg {

    @Id
    @Schema(description = "이모티콘ID")
    private Integer emoticonImgNo;

    @Schema(description = "이모티콘 상세ID")
    private String emoticonDetailId;

    @Schema(description = "이미지명")
    private String emoticonImgNm;

    @Schema(description = "이미지 확장자")
    private String imgExtNm;

    @Schema(description = "파일사이즈")
    private String imgFileSize;

    @Schema(description = "이미지 물리경로")
    private String fileUrlPath;

}
