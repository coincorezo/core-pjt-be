package com.pjt.core.board.entity;

import lombok.*;
import org.hibernate.annotations.Comment;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class BoardImg {

	private String imgNo;

	@Comment("게시판 아이디")
	private int boardId;

	@Comment("이미지 이름")
	private String emoticonImgNm;

	@Comment("이미지 확장자")
	private String imgExtNm;

	@Comment( "파일사이즈")
	private String imgFileSize;

	@Comment("이미지 물리 경로")
	private String fileUrlPath;

	@Comment( "이미지물리적이름")
	private String emoticonPhysicalNm;


}
