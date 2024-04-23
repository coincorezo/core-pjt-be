package com.pjt.core.board.dto;

import lombok.Data;

@Data
public class CreateBoardRequestDto {
	
	private Integer boardId;
	private String boardTitle;
	private String boardContent;
	private String boardWriter;
	private Integer boardViewCnt;
	private String category;
	private String boardStatus;
	private String regDt;
	private String updDt;
	private ReadBoardImgResponseDto boardImg;

}
