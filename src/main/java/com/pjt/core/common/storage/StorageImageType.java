package com.pjt.core.common.storage;

import lombok.Getter;

@Getter
public enum StorageImageType {
    BOARD("board", "게시판 이미지"),
    PROFILE("profile", "프로필 이미지"),
    EMOTICON("emoticon", "이모티콘 이미지"),
    ;

    private final String type;
    private final String description;

    StorageImageType(String type, String description) {
        this.type = type;
        this.description = description;
    }
}
