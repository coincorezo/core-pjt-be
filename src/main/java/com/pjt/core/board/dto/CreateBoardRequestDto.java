package com.pjt.core.board.dto;


import com.pjt.core.common.util.XssConverter;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class CreateBoardRequestDto {
	

	private Integer boardId;
	
	@NotBlank(message = "게시글 제목은 필수입니다.")
	private String boardTitle;
	
	@NotBlank(message = "게시글 내용은 필수입니다.")
	@XssConverter
//	@JsonSerialize(using = XssSerializer.class)
	private String boardContent;
	
	@NotBlank(message = "작성자는 필수입니다.")
	private String boardWriter;
	
	private Integer boardViewCnt;
	
	private String category;
	
	private String boardStatus;
	
	private String regDt;
	
	private String updDt;
	
	private ReadBoardImgResponseDto boardImg;

}
