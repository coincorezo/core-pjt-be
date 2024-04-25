package com.pjt.core.board.dto;

import lombok.Data;

@Data
public class FileResponseDto {

	private String uploadFilePath;
	private String savedName;
	
	// TODO DTO 분리 생각해보기
	private String imgNo;
	private Integer boardId;
	private String emoticonImgNm;
	private String imgExtNm;
	private Long imgFileSize;
	
}
