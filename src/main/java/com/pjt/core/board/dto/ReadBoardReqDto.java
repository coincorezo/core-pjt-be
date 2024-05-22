package com.pjt.core.board.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ReadBoardReqDto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "게시판 ID")
	private int boardId;

	@Schema(description = "게시판 제목")
	@NotBlank(message = "게시판 제목 필수입니다.")
	private String boardTitle;

	@Schema(description = "게시판 내용")
	@NotBlank(message = "게시판 내용 필수입니다.")
	private String boardContent;

	@Schema(description = "작성자")
	@NotBlank(message = "작성자 필수입니다.")
	private String boardWriter;

	@Schema(description = "조회수")
	private String boardViewCnt;

	@Schema(description = "게시글 카테고리")
	@NotBlank(message = "게시글 카테고리는 필수입니다.")
	private String category;

	@Schema(description = "게시글 상태값(비공개/공개/삭제")
	@NotBlank(message = "게시글 공개여부는 필수입니다.")
	private String boardStatus;

	@Schema(description = "등록일")
	private String regDt;

	@Schema(description = "수정일")
	private String uptDt;
}
