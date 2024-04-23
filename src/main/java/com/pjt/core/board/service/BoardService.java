package com.pjt.core.board.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.pjt.core.board.dto.CreateBoardRequestDto;
import com.pjt.core.board.dto.ReadBoardImgResponseDto;
import com.pjt.core.board.dto.ReadBoardResponseDto;
import com.pjt.core.board.repository.BoardMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	
	private final BoardMapper boardMapper;
	private final FileService fileService;

public List<ReadBoardResponseDto> getBoard(String category) {
		return boardMapper.getBoard(category);
	}

public ReadBoardResponseDto getDetail(String boardId) {
	// 상세 조회
	ReadBoardResponseDto dto =  boardMapper.getDetail(boardId);
	// 이미지 조회
	ReadBoardImgResponseDto img = boardMapper.getImage(boardId);
	
	dto.setBoardImg(img);
	
	return dto;
}

public String createBoard(CreateBoardRequestDto dto, List<MultipartFile> files) {
	// TODO 리턴타입 메세지 or 다른 형태 ?
	
	// 필수값 체크
	if(dto != null) {
		if(!StringUtils.hasText(dto.getBoardTitle())) {
			// 에러내기
		}
	} else {
		// 에러
	}
	
	if(files != null) {
		fileService.uploadFile(files);
	}
	
	// TODO 작업필요함~~~~~~~~
	
	return null;
}




}
