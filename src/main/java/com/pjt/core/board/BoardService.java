package com.pjt.core.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.pjt.core.board.dto.BoardDto;
import com.pjt.core.board.dto.CreateBoardImgReqDto;
import com.pjt.core.board.dto.CreateBoardReqDto;
import com.pjt.core.board.dto.CreateBoardResDto;
import com.pjt.core.board.dto.ReadBoardDtlReqDto;
import com.pjt.core.board.dto.ReadBoardDtlResDto;
import com.pjt.core.board.dto.ReadBoardListReqDto;
import com.pjt.core.board.dto.ReadReplyReqDto;
import com.pjt.core.board.dto.UpdateBoardReqDto;
import com.pjt.core.common.util.FileUploadUtile;

@Service
public class BoardService {
//	@Autowired
	private BoardMapper boardMapper;

	@Autowired
	public BoardService(BoardMapper boardMapper) {
		this.boardMapper = boardMapper;
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * <pre>
	 * 게시판 리스트
	 * @param ReadBoardListReqDto
	 * @return BoardDto
	 */
	public List<BoardDto> getBoardList(ReadBoardListReqDto boardReqDto) {

		List<BoardDto> boardList = boardMapper.getBoardList(boardReqDto);

		return boardList;
	}

	/**
	 * <pre>
	 * 게시판 등록
	 * @param CreateBoardReqDto
	 * @return CreateBoardResDto
	 */
	public CreateBoardResDto insertBoard( CreateBoardReqDto boardReqDto
			/*,MultipartHttpServletRequest multiRequest*/) throws Exception {
		CreateBoardResDto boardResDto = new CreateBoardResDto();
		/*insert*/
		boardMapper.insertBoard(boardReqDto);
		boardResDto.setBoardId(boardReqDto.getBoardId());


		// 물리적파일 업로드
		FileUploadUtile fileUploadUtile = new FileUploadUtile();
		/*
		 * Map<String, Object> fileInfo = fileUploadUtile.fileUpload(multiRequest);
		 * CreateBoardImgReqDto boardImgDto = new CreateBoardImgReqDto();
		 * boardImgDto.setBoardId(boardReqDto.getBoardId());
		 * boardImgDto.setEmoticonImgNm((String) fileInfo.get("fileName"));
		 * boardImgDto.setEmoticonPhysicalNm((String)
		 * fileInfo.get("emoticonPhysicalNm")); boardImgDto.setFileUrlPath((String)
		 * fileInfo.get("filePath")); boardImgDto.setImgExtNm((String)
		 * fileInfo.get("extension")); System.out.println(fileInfo.get("imgFileSize"));
		 * boardImgDto.setImgFileSize((Long) fileInfo.get("imgFileSize"));
		 * boardMapper.insertBoardImg(boardImgDto);
		 */

		return boardResDto;
	}

	/**
	 * <pre>
	 * 게시판 상세
	 * @param ReadBoardDtlReqDto
	 * @return ReadBoardDtlResDto
	 */
	public ReadBoardDtlResDto getBoardDtl(ReadBoardDtlReqDto boardReqDto) {

		ReadBoardDtlResDto boardResDto = new ReadBoardDtlResDto();
		boardResDto = boardMapper.getBoardDtl(boardReqDto);

		List<CreateBoardImgReqDto> boardImgDtoList = new ArrayList<CreateBoardImgReqDto>();

		boardImgDtoList = boardMapper.getBoardDtlImg(boardReqDto);
		boardResDto.setBoardImgdto(boardImgDtoList);
		return boardResDto;
	}

	/**
	 * <pre>
	 * 게시판 수정
	 * @param UpdateBoardReqDto
	 * @return CreateBoardResDto
	 */
	public CreateBoardResDto updateBoard(UpdateBoardReqDto updateBoardReqDto) {
		CreateBoardResDto updateBoard = new CreateBoardResDto();

		int saveCount = boardMapper.updateBoard(updateBoardReqDto);

		updateBoard.setBoardId(updateBoardReqDto.getBoardId());

		return updateBoard;
	}

	/**
	 * <pre>
	 * 게시판 삭세(delete)
	 * @param UpdateBoardReqDto
	 * @return CreateBoardResDto
	 */
	public CreateBoardResDto deleteBoard(UpdateBoardReqDto updateBoardReqDto) {
		CreateBoardResDto updateBoard = new CreateBoardResDto();

		int saveCount = boardMapper.updateBoard(updateBoardReqDto);

		updateBoard.setBoardId(updateBoardReqDto.getBoardId());

		return updateBoard;
	}

	/**
	 * <pre>
	 * 게시판 댓글 조회
	 * @param ReadBoardDtlReqDto
	 * @return ReadBoardDtlResDto
	 */
	public ReadReplyResDto getReply(ReadReplyReqDto boardReqDto) {

		ReadBoardDtlResDto boardResDto = new ReadBoardDtlResDto();
		boardResDto = boardMapper.getReply(boardReqDto);

		List<CreateBoardImgReqDto> boardImgDtoList = new ArrayList<CreateBoardImgReqDto>();

		boardImgDtoList = boardMapper.getBoardDtlImg(boardReqDto);
		boardResDto.setBoardImgdto(boardImgDtoList);
		return boardResDto;
	}
}
