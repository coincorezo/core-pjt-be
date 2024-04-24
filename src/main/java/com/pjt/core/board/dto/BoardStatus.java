package com.pjt.core.board.dto;

import lombok.Getter;

@Getter
public enum BoardStatus {

	PUBLIC("PUBLIC", "공개"),
	PRIVATE("PRIVATE", "비공개"),
	DELETE("DELETE", "삭제");

	private final String code;

	private final String name;

	BoardStatus(String code, String name)	{
	this.code = code;
	this.name = name;


}
}
