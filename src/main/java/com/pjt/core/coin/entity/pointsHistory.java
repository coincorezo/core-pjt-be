package com.pjt.core.coin.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class pointsHistory {

    private int history_id;

    private String userId;

    private int pointsChange;

    private int pointsAmount;

    private String reason;

    private LocalDateTime regDt;

}
