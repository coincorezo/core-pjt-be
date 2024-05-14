package com.pjt.core.board.dto;

import com.pjt.core.common.util.Paging;

import lombok.Data;

@Data
public class BoardRequestDto extends Paging {

	private String category;
	// 검색어
	private String keyword;
	// 검색 조건
	private String searchOption;
	// 게시글 번호
	private String boardId;
	
}
