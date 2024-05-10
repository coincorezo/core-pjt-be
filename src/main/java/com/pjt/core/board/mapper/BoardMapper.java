package com.pjt.core.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pjt.core.board.dto.BoardRequestDto;
import com.pjt.core.board.dto.CreateBoardRequestDto;
import com.pjt.core.board.dto.FileResponseDto;
import com.pjt.core.board.dto.ReadBoardImgResponseDto;
import com.pjt.core.board.dto.ReadBoardResponseDto;

import jakarta.validation.Valid;

@Mapper
public interface BoardMapper {

	List<ReadBoardResponseDto> getBoard(BoardRequestDto boardRequestDto);

	ReadBoardResponseDto getDetail(String boardId);

	List<ReadBoardImgResponseDto> getImage(String boardId);

	int createBoard(CreateBoardRequestDto dto);

	void createBoardImg(FileResponseDto savedFile);

	int getBoardTotalCount(@Valid BoardRequestDto boardRequestDto);

}
