package com.pjt.core.board.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.pjt.core.board.dto.CreateBoardRequestDto;
import com.pjt.core.board.dto.FileResponseDto;
import com.pjt.core.board.dto.ReadBoardImgResponseDto;
import com.pjt.core.board.dto.ReadBoardResponseDto;

@Mapper
public interface BoardMapper {

	List<ReadBoardResponseDto> getBoard(String category);

	ReadBoardResponseDto getDetail(String boardId);

	List<ReadBoardImgResponseDto> getImage(String boardId);

	int createBoard(CreateBoardRequestDto dto);

	void createBoardImg(FileResponseDto savedFile);

}
