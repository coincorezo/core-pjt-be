package com.pjt.core.board.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.pjt.core.board.dto.*;
import com.pjt.core.board.dto.boardJ.DeleteReplyRequestDto;
import com.pjt.core.board.dto.boardJ.UpdateReplyRequestDto;
import com.pjt.core.board.exception.BoardException;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.pjt.core.board.mapper.BoardJMapper;
import com.pjt.core.common.error.exception.NoDataException;
import com.pjt.core.common.error.response.ErrorCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardJService {
	
	private final BoardJMapper boardJMapper;
	private final FileService fileService;


public List<ReadBoardResponseDto> getBoard(ReadBoardRequestDto dto) {
		return boardJMapper.getBoard(dto);
	}

public ReadBoardResponseDto getDetail(String boardId) throws Exception {
	if(!StringUtils.hasText(boardId)) {
		throw new Exception("게시글 ID가 없습니다.");
	}
	
	// 상세 조회
	ReadBoardResponseDto dto =  boardJMapper.getDetail(boardId);
	Optional.ofNullable(dto).orElseThrow(() -> new NoDataException(ErrorCode.NO_DATA));
	
	// 이미지 조회
	List<ReadBoardImgResponseDto> img = boardJMapper.getImage(boardId);
	
	// 댓글 조회
	List<ReplyResponseDto> reply = boardJMapper.getReply(boardId);

	dto.setBoardImg(img);
	dto.setReply(reply);
	
	return dto;
}

public String createBoard(CreateBoardRequestDto dto, List<MultipartFile> files) throws Exception  {

	List<FileResponseDto> savedFiles = new ArrayList<>();
	// 필수값 체크
	if(dto == null) {
		throw new NoDataException(ErrorCode.INTERNAL_SERVER_ERROR);
	} 
	
	// 게시글 저장
	dto.setBoardContent(Jsoup.clean(dto.getBoardContent(), Safelist.basic()));
	
	int result = boardJMapper.createBoard(dto);
	
	if(result == 0) {
		throw new Exception("저장 중 오류가 발생했습니다.");
	}
	
	
	if(!CollectionUtils.isEmpty(files)) {
				try {
					// 파일 저장
					savedFiles = fileService.uploadFile(dto.getBoardId(), files);
					
					// DB 파일 저장
					for(FileResponseDto savedFile : savedFiles) {
						boardJMapper.createBoardImg(savedFile);
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

public String updateBoard(CreateBoardRequestDto dto, List<MultipartFile> files, List<String> deleteImgNo) throws IOException {

	List<FileResponseDto> savedFiles = new ArrayList<>();

	if(dto == null) {
		throw new NoDataException(ErrorCode.INTERNAL_SERVER_ERROR);
	}

	if(CollectionUtils.isEmpty(files)) {
		throw new NoDataException(ErrorCode.INTERNAL_SERVER_ERROR);
	}

	// 파일 삭제
	if(!CollectionUtils.isEmpty(deleteImgNo)) {
		fileService.deleteImg(deleteImgNo);
	}

	// 새로운 파일 업로드
	if(!CollectionUtils.isEmpty(files)) {
		savedFiles = fileService.uploadFile(dto.getBoardId(), files);

		for(FileResponseDto savedFile : savedFiles) {
			boardJMapper.createBoardImg(savedFile);
		}
	}

	// 게시글 수정
	boolean isUpdated = boardJMapper.updateBoard(dto);

	return null;
}


	public String createReply(ReplyRequestDto dto) {
		boardJMapper.createReply(dto);
		return "저장 완료";
	}


	public String updateReply(UpdateReplyRequestDto dto) {
		// 댓글 존재 여부 확인
		int replyCount = boardJMapper.getExistReply(dto);

		if(replyCount == 0) {
			throw new BoardException(ErrorCode.NO_DATA);
		}

		boardJMapper.updateReply(dto);
		return "저장 완료";
	}

	public String deleteReply(DeleteReplyRequestDto dto) {
		UpdateReplyRequestDto updateDto = new UpdateReplyRequestDto();
		updateDto.setBoardId(dto.getBoardId());
		updateDto.setReplyId(dto.getReplyId());

		// 댓글 존재 여부 확인
		int replyCount = boardJMapper.getExistReply(updateDto);

		if(replyCount == 0) {
			throw new BoardException(ErrorCode.NO_DATA);
		}

		// 댓글 삭제(사용여부 Y -> N)
		boardJMapper.updateReplyUseYn(dto);

		return "삭제 완료";
	}
}
