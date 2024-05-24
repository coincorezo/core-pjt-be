package com.pjt.core.emoticon.dto;

import com.pjt.core.emoticon.entity.EmoticonImg;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

/*
 * <pre>
 * 이모티콘 상세 Entity
 * </pre>
 * @author      : jayeon
 * @date        : 2024-05-24
*/
@Data
public class EmoticonDetail extends EmoticonImg{

    @Schema(description = "이모티콘 상세ID")
    private String emoticonDetailId;

    @Schema(description = "이모티콘 ID")
    private Integer emoticonId;

    @Schema(description = "이모티콘명")
    private String emoticonNm;

    @Schema(description = "등록일자")
    private String regDt;

    @Schema(description = "등록자")
    private String regId;


}
