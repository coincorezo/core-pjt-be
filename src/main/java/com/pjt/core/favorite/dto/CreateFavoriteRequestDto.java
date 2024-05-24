package com.pjt.core.favorite.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CreateFavoriteRequestDto {

    @Schema(description = "이모티콘ID")
    private Integer emoticonId;

    @Schema(description = "사용자ID", hidden = true)
    private String userId;

}
