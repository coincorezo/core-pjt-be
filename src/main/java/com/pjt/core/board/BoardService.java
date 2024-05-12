package com.pjt.core.board;

import java.util.ArrayList;
import java.util.List;

import com.pjt.core.board.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pjt.core.common.util.FileUploadUtile;

@Service
public class BoardService {
	@Autowired
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
		int saveCount = boardMapper.insertBoard(boardReqDto);
	 	if(saveCount > 0) {
			boardResDto.setBoardId(boardReqDto.getBoardId());
			boardResDto.setMessage("게시글이 등록되었습니다");

		 }else{
			 boardResDto.setMessage("게시글 등록되지 않았습니다");

		}

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
		if(!boardImgDtoList.isEmpty()) {
			boardResDto.setBoardImgdto(boardImgDtoList);
		}
		List<ReadReplyResDto> replyList = this.getReplyList(boardReqDto.getBoardId());
		if(!replyList.isEmpty()) {
			boardResDto.setReplyList(replyList);
		}
		return boardResDto;
	}

		/**
         * <pre>
         * 게시판 수정
         * @param UpdateBoardReqDto
         * @return CreateBoardResDto
         */
	public CreateBoardResDto updateBoard(UpdateBoardReqDto updateBoardReqDto) {
		// FIXME: 게시글 수정 시 없는 글은 어떻게 처리할것인지

		/* 게시글 여부 확인 */
		ReadBoardDtlReqDto readBoardDtlReqDto = new ReadBoardDtlReqDto();
		ReadBoardDtlResDto readBoardDtlResDto = new ReadBoardDtlResDto();
		readBoardDtlReqDto.setBoardId(updateBoardReqDto.getBoardId());
		readBoardDtlResDto = this.getBoardDtl(readBoardDtlReqDto);

		int saveCount = 0;
		/* 게시글 여부 확인 & 게시글 ID 같은지 확인 후 게시글 수정 */
		if(readBoardDtlResDto !=null && updateBoardReqDto.getBoardId()==readBoardDtlResDto.getBoardId()) {
			saveCount = boardMapper.updateBoard(updateBoardReqDto);
		}

		CreateBoardResDto updateBoard = new CreateBoardResDto();
		if(saveCount>0) {
			updateBoard.setBoardId(updateBoardReqDto.getBoardId());
			updateBoard.setMessage("게시물 수정완료 되었습니다.");
		}else{
			updateBoard.setMessage("게시물 수정 불가 .");
		}
		return updateBoard;
	}

	/**
	 * <pre>
	 * 게시판 삭제(delete)
	 * @param UpdateBoardReqDto
	 * @return CreateBoardResDto
	 */
	public CreateBoardResDto deleteBoard(UpdateBoardReqDto updateBoardReqDto) {
		// FIXME: 게시글 삭제 시 없는 글은 어떻게 처리할것인지

		/* 게시글 여부 확인 */
		ReadBoardDtlReqDto readBoardDtlReqDto = new ReadBoardDtlReqDto();
		ReadBoardDtlResDto readBoardDtlResDto = new ReadBoardDtlResDto();
		readBoardDtlReqDto.setBoardId(updateBoardReqDto.getBoardId());
		readBoardDtlResDto = this.getBoardDtl(readBoardDtlReqDto);


		/* 게시글 여부 확인 후 boardStatus DELETE 변경*/
		CreateBoardResDto updateBoard = new CreateBoardResDto();
		if(readBoardDtlResDto !=null  && (updateBoardReqDto.getBoardId()) ==(readBoardDtlResDto.getBoardId())) {
			int saveCount = boardMapper.updateBoard(updateBoardReqDto);
			
			updateBoard.setBoardId(updateBoardReqDto.getBoardId());
			if(saveCount >0 ) {
				updateBoard.setMessage("삭제되었습니다.");
			}
		}else{
			updateBoard.setMessage("삭제할 게시물이 없습니다.");
		}



		return updateBoard;
	}

	public List<ReadReplyResDto> getReplyList (int boardId) {
		List<ReadReplyResDto> resDto = new ArrayList<ReadReplyResDto>();

		resDto = boardMapper.getReply(boardId);
		return resDto;

	}

	public String insertReply(CreateReplyReqDto replyReqDto) {
		// FIXME: 존재하지 않는 게시글에도 댓글이 작성되는건지

		/*게시글 여부 확인 */
		ReadBoardDtlReqDto readBoardReqDto = new ReadBoardDtlReqDto();
		ReadBoardDtlResDto readBoardDtlResDto = new ReadBoardDtlResDto();
		readBoardReqDto.setBoardId(replyReqDto.getBoardId());
		readBoardDtlResDto =this.getBoardDtl(readBoardReqDto);

		/*게시글 있으면 댓글 등록 */
		if(readBoardDtlResDto !=null && (replyReqDto.getBoardId()) ==(readBoardDtlResDto.getBoardId())
				&& ("PUBLIC").equals(readBoardDtlResDto.getBoardStatus())) {
			int saveCount = boardMapper.insertReply(replyReqDto);

			return "저장되었습니다";

		}else{

			return "없는 게시물입니다";

		}


	}

    public String updateReply(UpdateReplyReqDto replyReqDto) {
		/*게시글 여부 확인 */
		ReadBoardDtlReqDto readBoardReqDto = new ReadBoardDtlReqDto();
		ReadBoardDtlResDto readBoardDtlResDto = new ReadBoardDtlResDto();
		readBoardReqDto.setBoardId(replyReqDto.getBoardId());
		readBoardDtlResDto =this.getBoardDtl(readBoardReqDto);

		/*게시글 있으면 댓글  삭제 || 수정 */
		if(readBoardDtlResDto !=null && (replyReqDto.getBoardId()) ==(readBoardDtlResDto.getBoardId())) {
			int saveCount = boardMapper.updateReply(replyReqDto);
			if (saveCount > 0) {
				return  ("N").equals(replyReqDto.getUseYn()) ? "삭제되었습니다." : "수정되었습니다";
			} else {
				return  (!("N").equals(replyReqDto.getUseYn())) ? "수정 되지 않았습니다." : "삭제되지 않았습니다";
			}
		}else {
			return  ("N").equals(replyReqDto.getUseYn()) ? "삭제되지 않았습니다" : "수정 되지 않았습니다.";
		}

	}
}
