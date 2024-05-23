package com.pjt.core.coin.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateCoinReqDto {

    @Schema(description = "사용자 ID")
    @NotBlank(message = "사용자 ID 필수입니다.")
    private String userId;

    @Schema(description = "변경포인트")
    @NotNull(message = "변경포인트 필수입니다.")
    private int pointsChange;

    @Schema(description = "최종포인트")
    private int pointsAmount;

//    @Schema(description = "변경사유")
//    private String reason;

    @Schema(description = "변경사유 enum")
    @NotBlank(message = "변경사유 필수입니다.")
    private Reason reason;

    @Schema(description = "변경사유 enum")
    private String coinReason;

    @Schema(description = "사용일자")
    @NotBlank(message = "사용일자 필수입니다.")
    private LocalDateTime regDt;
}
