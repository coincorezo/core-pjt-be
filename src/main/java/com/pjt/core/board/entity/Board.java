package com.pjt.core.board.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Board {

	private int boardId;

	@Schema(description = "게시판 제목")
	private String boardTitle;

	@Schema(description = "게시판 내용")
	private String boardContent;

	@Schema(description = "작성자")
	private String boardWiter;

	@Schema(description = "조회수")
	private String boardViewCnt;

	@Schema(description = "게시글 카테고리")
	private String category;

	@Schema(description = "게시글 상태값(비공개/공개/삭제")
	private String boardStatus;

	@Schema(description = "등록일")
	private String regDt;

	@Schema(description = "수정일")
	private String uptDt;
}
