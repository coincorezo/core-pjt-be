package com.pjt.core.common.storage.dto;

import com.pjt.core.common.storage.entity.Storage;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter

public class StorageResponse {

    @Schema(description = "이미지 ID")
    private Long imgId;

    @Schema(description = "이미지 원본명")
    private String imgOriginNm;

    @Schema(description = "이미지 저장명")
    private String imgSaveNm;

    @Schema(description = "이미지 확장자")
    private String imgExt;

    @Schema(description = "파일 URL 경로")
    private String fileUrlPath;

    @Builder
    public StorageResponse(Long imgId, String imgOriginNm, String imgSaveNm, String imgExt, String fileUrlPath) {
        this.imgId = imgId;
        this.imgOriginNm = imgOriginNm;
        this.imgSaveNm = imgSaveNm;
        this.imgExt = imgExt;
        this.fileUrlPath = fileUrlPath;
    }

    public static StorageResponse fromEntity(Storage storage) {
       return StorageResponse.builder()
                    .imgId(storage.getImgId())
                    .imgOriginNm(storage.getImgOriginNm())
                    .imgSaveNm(storage.getImgSaveNm())
                    .imgExt(storage.getImgExt())
                    .fileUrlPath(storage.getFileUrlPath())
                    .build();
    }

}