package com.pjt.core.board.dto;

import java.security.Timestamp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ReadReplyReqDto {

	@Schema(name="게시판 ID")
	private int boardId;




}
