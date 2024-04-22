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
import com.pjt.core.board.dto.ReadBoardImgReqDto;
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
		//int saveCount = boardMapper.insertBoard(boardReqDto);
		//boardResDto = boardMapper.insertBoard(boardReqDto);
		boardMapper.insertBoard(boardReqDto);
		System.out.println(boardReqDto.getBoardId());
		FileUploadUtile fileUploadUtile = new FileUploadUtile();

		// 물리적파일 업로드
		Map<String, Object> fileInfo = fileUploadUtile.fileUpload(multiRequest);
		ReadBoardImgReqDto boardImgDto = new ReadBoardImgReqDto();
		boardImgDto.setBoardId(boardReqDto.getBoardId());
		boardImgDto.setEmoticonImgNm((String) fileInfo.get("fileName"));
		boardImgDto.setEmoticonPhysicalNm((String) fileInfo.get("emoticonPhysicalNm"));
		boardImgDto.setFileUrlPath((String) fileInfo.get("filePath"));
		boardImgDto.setImgExtNm((String) fileInfo.get("extension"));
		System.out.println(fileInfo.get("imgFileSize"));
		boardImgDto.setImgFileSize((Long) fileInfo.get("imgFileSize"));
		boardMapper.insertBoardImg(boardImgDto);
//

		return null;
	}

}
