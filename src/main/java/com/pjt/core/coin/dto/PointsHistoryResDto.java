package com.pjt.core.coin.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * <pre>
 *  코인 이력
 * </pre>
 *
 * @author :  KangMinJi
 * @date :  2024-05-24
 */
@Data
public class PointsHistoryResDto {
    @Schema(description = "히스토리 ID")
    private int historyId;

    @Schema(description = "사용자 ID")
    private String userId;

    @Schema(description = "변경포인트")
    private int pointsChange;

    @Schema(description = "최종포인트")
    private int pointsAmount;

    @Schema(description = "변경사유")
    private String reason;

    @Schema(description = "사용일자")
    private String regDt;

    @Schema(description = "적립/차감 type")
    private String coinType;


}
