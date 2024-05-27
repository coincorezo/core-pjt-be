package com.pjt.core.board.dto;

import lombok.Data;

/*
 * <pre>
 * 파일 저장 response dto
 * </pre>
 * @author      : jayeon
 * @date        : 2024-05-27
*/
@Data
public class FileResponseDto {

	private String uploadFilePath;
	private String savedName;

	private String imgNo;
	private Integer boardId;
	private String emoticonImgNm;
	private String emoticonDetailId;
	private String imgExtNm;
	private Long imgFileSize;
	
}
