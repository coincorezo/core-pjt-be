package com.pjt.core.common.storage.dto;

import com.pjt.core.common.storage.entity.Storage;
import lombok.Builder;
import lombok.Getter;

@Getter
public class StorageResponse {

    private Long imgId;

    private String imgOriginNm;

    private String imgSaveNm;

    private String imgExt;

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