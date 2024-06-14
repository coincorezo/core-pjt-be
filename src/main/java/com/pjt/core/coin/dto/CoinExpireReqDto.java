package com.pjt.core.coin.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CoinExpireReqDto {

    @Schema(description = "사용자 ID")
    private String userId;

    @Schema(description = "시작날짜 / 2024-05-22")
    private LocalDate startDate;

    @Schema(description = "종료날짜 / 2024-05-22")
    private LocalDate endDate;

    @Schema(description = "조회기간 /1/3/6")
    private int dateFlg;
}
