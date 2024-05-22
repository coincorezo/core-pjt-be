package com.pjt.core.board.dto;

import lombok.Data;

@Data
public class ReplyResponseDto {

    private Integer replyId;
    private Integer boardId;
    private String replyComment;
    private String regDt;
    private String updateDt;
    private String useYn;
}
