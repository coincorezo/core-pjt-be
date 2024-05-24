package com.pjt.core.coin.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class PointsHistoryReqDto {


    @Schema(description = "사용자 ID")
    private String userId;

    @Schema(description = "시작날짜 / 2024-05-22")
    private LocalDate startDate;

    @Schema(description = "종료날짜 / 2024-05-22")
    private LocalDate endDate;

    @Schema(description = "조회기간 /1M/3M/6M")
    private String dateFlg;
}
