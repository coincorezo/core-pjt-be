package com.pjt.core.board;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pjt.core.board.dto.BoardDto;
import com.pjt.core.board.dto.CreateBoardReqDto;
import com.pjt.core.board.dto.ReadBoardReqDto;

@Mapper
public interface BoardMapper {

	List<BoardDto> getBoardList(CreateBoardReqDto boardDto);

	/* 게시물 등록 */
	int insertBoard(ReadBoardReqDto boardReqDto);

}
