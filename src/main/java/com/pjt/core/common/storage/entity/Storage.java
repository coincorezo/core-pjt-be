package com.pjt.core.common.storage.entity;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Storage {

    private String typeKey;

    private String imagRealNm;

    private String imgSaveNm;

    private String imgExtNm;

    private Long imgFileSize;

    private String fileUrlPath;

}
