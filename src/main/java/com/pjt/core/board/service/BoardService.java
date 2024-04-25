package com.pjt.core.board.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.pjt.core.board.dto.CreateBoardRequestDto;
import com.pjt.core.board.dto.FileRequestDto;
import com.pjt.core.board.dto.FileResponseDto;
import com.pjt.core.board.dto.ReadBoardImgResponseDto;
import com.pjt.core.board.dto.ReadBoardResponseDto;
import com.pjt.core.board.mapper.BoardMapper;
import com.pjt.core.common.error.exception.NoDataException;
import com.pjt.core.common.error.response.ErrorCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	
	private final BoardMapper boardMapper;
	private final FileService fileService;

public List<ReadBoardResponseDto> getBoard(String category) {
		return boardMapper.getBoard(category);
	}

public ReadBoardResponseDto getDetail(String boardId) throws Exception {
	if(!StringUtils.hasText(boardId)) {
		// throw new NoDataException(ErrorCode.INVALID_INPUT_VALUE);
		throw new Exception("게시글 ID가 없습니다.");
	}
	
	// 상세 조회
	ReadBoardResponseDto dto =  boardMapper.getDetail(boardId);
	Optional.ofNullable(dto).orElseThrow(() -> new NoDataException(ErrorCode.NO_DATA));
	
	// 이미지 조회
	List<ReadBoardImgResponseDto> img = boardMapper.getImage(boardId);
	
	dto.setBoardImg(img);
	
	return dto;
}

public String createBoard(CreateBoardRequestDto dto, List<MultipartFile> files) throws Exception  {

	List<FileResponseDto> savedFiles = new ArrayList<>();
	// 필수값 체크
	if(dto != null) {
		if(!StringUtils.hasText(dto.getBoardTitle())) {
			throw new NoDataException(ErrorCode.INVALID_INPUT_VALUE);
		}
		
		if(!StringUtils.hasText(dto.getBoardContent())) {
			throw new NoDataException(ErrorCode.INVALID_INPUT_VALUE);
		}
		
		if(!StringUtils.hasText(dto.getBoardWriter())) {
			throw new NoDataException(ErrorCode.INVALID_INPUT_VALUE);
		}
	} else {
		throw new NoDataException(ErrorCode.INVALID_INPUT_VALUE);
	}
	
	// 게시글 저장
	int result = boardMapper.createBoard(dto);
	
	if(result == 0) {
		throw new Exception("저장 중 오류가 발생했습니다.");
	}
	
	
	if(!CollectionUtils.isEmpty(files)) {
				try {
					// 파일 저장
					savedFiles = fileService.uploadFile(dto.getCategory(), files);
				} catch (IllegalStateException | IOException e) {
					for(FileResponseDto file : savedFiles) {
						// 업로드 실패시 파일 삭제
						new File(file.getUploadFilePath() + file.getSavedName()).delete();
					}
				}
	}
	
	return "저장완료";

}




}
