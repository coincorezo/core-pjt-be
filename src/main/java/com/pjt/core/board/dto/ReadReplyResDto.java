package com.pjt.core.board.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.security.Timestamp;
import java.util.Date;

@Data
public class ReadReplyResDto {
    @Schema(description="댓글 아이디")
    private int replyId;

    @Schema(description="게시판 ID")
    private int boardId;

    @Schema(description="댓글 내용")
    private String replyComment;

    @Schema(description="등록일자")
    private Date regDt;

    @Schema(description="수정일자")
    private Date updateDt;

    @Schema(description="삭제 여부")
    private String useYn;

}
