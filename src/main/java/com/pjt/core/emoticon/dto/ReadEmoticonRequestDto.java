package com.pjt.core.emoticon.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
/*
 * <pre>
 * 이모티콘 조회 request Dto
 * </pre>
 * @author      : jayeon
 * @date        : 2024-05-24
*/
@Data
public class ReadEmoticonRequestDto {

    @Schema(description = "사용자 ID", hidden = true)
    private String userId;

    @Schema(description = "이모티콘 ID", hidden = true)
    private Integer emoticonId;

    @Schema(description = "검색조건")
    private String searchCode;
}
