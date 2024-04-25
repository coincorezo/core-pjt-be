package com.pjt.core.board.dto;

import java.util.List;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class ReadBoardResponseDto {

	private Integer boardId;
	private String boardTitle;
	private String boardContent;
	private String boardWriter;
	private Integer boardViewCnt;
	private String category;
	private String boardStatus;
	private String regDt;
	private String updDt;
	private List<ReadBoardImgResponseDto> boardImg;
	
}
