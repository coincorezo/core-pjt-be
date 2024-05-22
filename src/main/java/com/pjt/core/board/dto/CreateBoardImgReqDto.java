package com.pjt.core.board.dto;

import org.hibernate.annotations.Comment;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class CreateBoardImgReqDto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description ="이미지 번호")
	private int imgNo;

	@Schema(description ="게시판 아이디")
	private int boardId;

	@Schema(description ="이미지 이름")
	private String emoticonImgNm;

	@Schema(description ="이미지 확장자")
	private String imgExtNm;

	@Schema(description = "파일사이즈")
	private Long imgFileSize;

	@Schema(description ="이미지 물리 경로")
	private String fileUrlPath;

	@Schema(description = "이미지물리적이름")
	private String emoticonPhysicalNm;



}
