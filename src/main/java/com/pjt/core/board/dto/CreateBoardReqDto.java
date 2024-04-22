package com.pjt.core.board.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CreateBoardReqDto {

	@Schema(description = "게시글 카테고리")
	private String category;

	@Schema(description = "메인조회용 board 게시판 구별 flg maim/board")
	private String mainFlg;

	@Schema(description = "제목=1, 제목+내용=2, 내용 검색=3")
	private String searchFlg;

	@Schema(description = "게시판 검색text")
	private String searchText;

}
