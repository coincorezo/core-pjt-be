package com.pjt.core.board.dto;

import lombok.Data;

@Data
public class FileResponseDto {

	private String uploadFilePath;
	private String savedName;

	private String imgNo;
	private Integer boardId;
	private String emoticonImgNm;
	private String imgExtNm;
	private Long imgFileSize;
	
}
