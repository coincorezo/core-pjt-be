package com.pjt.core.emoticon.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CreateEmoticonDetailRequestDto {

    @Schema(description = "이모티콘 상세ID", hidden = true)
    private String emoticonDetailId;

    @Schema(description = "이모티콘ID", hidden = true)
    private Integer emoticonId;

    @Schema(description = "이모티콘명")
    private String emoticonNm;

    @Schema(description = "등록자", hidden = true)
    private String userId;

}
