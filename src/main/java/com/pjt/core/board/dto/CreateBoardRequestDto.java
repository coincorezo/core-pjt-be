package com.pjt.core.board.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateBoardRequestDto {
	
	@NotBlank
	private Integer boardId;
	@NotBlank
	private String boardTitle;
	@NotBlank
	private String boardContent;
	@NotBlank
	private String boardWriter;
	private Integer boardViewCnt;
	private String category;
	private String boardStatus;
	private String regDt;
	private String updDt;
	private ReadBoardImgResponseDto boardImg;

}
