package com.pjt.core.board.dto;

import lombok.Data;

@Data
public class ReadBoardImgResponseDto {

	private String imgNo;
	private Integer boardId;
	private String emoticonImgNm;
	private String imgExtNm;
	private String imgFileSize;
	private String fileUrlPath;
}
