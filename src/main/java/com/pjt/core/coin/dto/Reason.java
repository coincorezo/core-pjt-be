package com.pjt.core.coin.dto;

import lombok.Getter;

@Getter
public enum Reason {

    BOARD("accumulate", "게시판 등록 적립", "적립"),
    PURCHASE("PURCHASE", "이모티콘 구매 포인트 사용", "차감"),
    DISPEND("DISPEND", "이모티콘 판매 포인트 지급", "적립");

    private final String code;
    private final String name;
    private final String description;

    Reason(String code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }

}
