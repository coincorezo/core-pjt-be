package com.pjt.core.board.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.pjt.core.board.dto.CreateBoardRequestDto;
import com.pjt.core.board.dto.FileResponseDto;
import com.pjt.core.board.dto.ReadBoardImgResponseDto;
import com.pjt.core.board.dto.ReadBoardResponseDto;
import com.pjt.core.board.mapper.BoardMapper;
import com.pjt.core.common.error.exception.NoDataException;
import com.pjt.core.common.error.response.ErrorCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
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
	if(dto == null) {
		throw new NoDataException(ErrorCode.INTERNAL_SERVER_ERROR);
	} 
	
	// 게시글 저장
	// dto.setBoardContent(Jsoup.clean(dto.getBoardContent(), Safelist.basic()));
	
	int result = boardMapper.createBoard(dto);
	
	if(result == 0) {
		throw new Exception("저장 중 오류가 발생했습니다.");
	}
	
	
	if(!CollectionUtils.isEmpty(files)) {
				try {
					// 파일 저장
					savedFiles = fileService.uploadFile(dto.getBoardId(), files);
					
					// DB 파일 저장
					for(FileResponseDto savedFile : savedFiles) {
						boardMapper.createBoardImg(savedFile);
					}
				} catch (IllegalStateException | IOException e) {
					for(FileResponseDto file : savedFiles) {
						// 업로드 실패시 파일 삭제
						new File(file.getUploadFilePath() + file.getSavedName()).delete();
					}
				}
	}

	
	return "저장완료";

}

public String test(CreateBoardRequestDto dto) {
	String data = dto.getBoardContent();
	convertData(data);
	return data;
}

public String convertData(String data) {
	
	// 변환할 HTML 태그들
	Map<String, String> converterMap = new HashMap<>();
	converterMap.put("&", "&amp;");
	converterMap.put("<", "&lt;");
	converterMap.put(">", "&gt;");
	converterMap.put("\\(", "&#40;");
	converterMap.put("\\)", "&#41;");
	converterMap.put("#", "&#35;");
	converterMap.put("&", "&#38;");
	converterMap.put("'", "&#39;");
	converterMap.put(" ", "&nbsp;");
	converterMap.put("=", "&#61;");
	converterMap.put("[\\\\\\\"\\\\\\'][\\\\s]*javascript:(.*)[\\\\\\\"\\\\\\']", "\"\"");
	converterMap.put("script", "");
	converterMap.put("eval\\((.*)\\)", "");
	
	for(Map.Entry<String, String> entry : converterMap.entrySet()) {
		data = data.replaceAll(entry.getKey(), entry.getValue());
	}
	
	return data;
}




}
