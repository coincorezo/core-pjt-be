package com.pjt.core.coin.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PointsHistoryResDto {
    @Schema(description = "히스토리 ID")
    private int history_id;

    @Schema(description = "사용자 ID")
    private String userId;

    @Schema(description = "변경포인트")
    private int pointsChange;

    @Schema(description = "최종포인트")
    private int pointsAmount;

    @Schema(description = "변경사유")
    private String reason;

    @Schema(description = "사용일자")
    private LocalDateTime regDt;

}
