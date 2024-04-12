package com.pjt.core.board;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.pjt.core.board.dto.BoardDto;
import com.pjt.core.board.dto.CreateBoardReqDto;
import com.pjt.core.board.dto.ReadBoardReqDto;
import com.pjt.core.board.dto.ReadBoardResDto;
import com.pjt.core.common.util.FileUploadUtile;

@Service
public class BoardService {
	@Autowired
	BoardMapper boardMapper;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public List<BoardDto> getBoardList(CreateBoardReqDto boardReqDto) {
		List<BoardDto> boardList = boardMapper.getBoardList(boardReqDto);

		return boardList;
	}

	/* 게시판 업로드 */
	public ReadBoardResDto insertBoard(/* @Validated */ReadBoardReqDto boardReqDto,
			MultipartHttpServletRequest multiRequest) throws Exception {
		ReadBoardResDto boardResDto = new ReadBoardResDto();
		// board insert
		// int saveCount = boardMapper.insertBoard(boardReqDto);
		FileUploadUtile fileUploadUtile = new FileUploadUtile();
		// 물리적파일 업로드
		Map<String, Object> fileInfo = fileUploadUtile.fileUpload(multiRequest);

		return null;
	}

}
