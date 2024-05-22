package com.pjt.core.board.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class ReadBoardDtlReqDto {

	@Schema(name="게시판ID")
	private int boardId;

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}


}
