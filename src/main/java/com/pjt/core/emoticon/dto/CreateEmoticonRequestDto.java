package com.pjt.core.emoticon.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateEmoticonRequestDto {

    @Schema(description = "이모티콘 ID", hidden = true)
    private Integer emoticonId;

    @Schema(description = "이모티콘 제목")
    @NotBlank(message = "이모티콘 제목을 입력해주세요.")
    private String emoticonTitle;

    @Schema(description = "이모티콘 가격")
    @NotNull(message = "이모티콘 가격을 입력해주세요.")
    private Integer emoticonPrice;

    @Schema(description = "등록자", hidden = true)
    private String userId;
}
