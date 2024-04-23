package com.pjt.core.board.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pjt.core.board.dto.ReadBoardImgResponseDto;
import com.pjt.core.board.dto.ReadBoardResponseDto;

@Mapper
public interface BoardMapper {

	List<ReadBoardResponseDto> getBoard(String category);

	ReadBoardResponseDto getDetail(String boardId);

	ReadBoardImgResponseDto getImage(String boardId);

}
