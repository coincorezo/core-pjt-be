package com.pjt.core.coin.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PointsHistoryReqDto {


    @Schema(description = "사용자 ID")
    private String userId;

    @Schema(description = "사용일자")
    private LocalDateTime regDt;

    @Schema(description = "시작날짜")
    private LocalDateTime startDate;

    @Schema(description = "종료날짜")
    private LocalDateTime endDate;
}
