package com.pjt.core.board.dto;

import java.security.Timestamp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ReadReplyReqDto {
	@Schema(name="댓글 아이디")
	private int replyId;

	@Schema(name="게시판 ID")
	private int boardId;

	@Schema(name="댓글 내용")
	private String replyComment;

	@Schema(name="등록일자")
	private Timestamp regDt;

	@Schema(name="수정일자")
	private Timestamp updateDt;

	@Schema(name="삭제 여부")
	private String delYn;



}
