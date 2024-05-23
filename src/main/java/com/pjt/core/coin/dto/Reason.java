package com.pjt.core.coin.dto;

import lombok.Getter;

@Getter
public enum Reason {

    BOARD("accumulate", "게시판 등록 적립"),
    PURCHASE("PURCHASE", "이모티콘 구매 포인트 사용"),
    DISPEND("DISPEND", "이모티콘 판매 포인트 지급");

    private final String code;
    private final String name;

    Reason(String code, String name) {
        this.code = code;
        this.name = name;
    }

}
