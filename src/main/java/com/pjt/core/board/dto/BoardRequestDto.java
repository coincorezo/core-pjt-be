package com.pjt.core.board.dto;

import com.pjt.core.common.util.Paging;

import lombok.Data;

@Data
public class BoardRequestDto extends Paging {

	private String category;
	private String keyword;
}
