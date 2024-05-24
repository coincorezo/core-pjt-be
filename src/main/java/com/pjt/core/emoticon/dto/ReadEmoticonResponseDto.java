package com.pjt.core.emoticon.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/*
 * <pre>
 *  이모티콘 조회 Response dto
 * </pre>
 * @author      : jayeon
 * @date        : 2024-05-24
*/
@Data
public class ReadEmoticonResponseDto {

    @Schema(description = "이모티콘 ID")
    private Integer emoticonId;

    @Schema(description = "이모티콘 제목")
    private String emoticonTitle;

    @Schema(description = "이모티콘 가격")
    private Integer emoticonPrice;

    @Schema(description = "총 좋아요 수")
    private Integer favoriteCount;

    @Schema(description = "사용자 좋아요 여부")
    private String favoriteYn;
}
