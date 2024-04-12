package com.pjt.core.board.dto;

import com.pjt.core.common.util.Schema;

import lombok.Data;

@Data
public class ReadBoardResDto {

	@Schema(description = "게시글 ID")
	private String boardId;

}
