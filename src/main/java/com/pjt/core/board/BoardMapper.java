package com.pjt.core.board;

import java.util.List;
import java.util.Map;

import com.pjt.core.board.dto.*;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {
	/* 게시판 리스트 */
	List<BoardDto> getBoardList(ReadBoardListReqDto boardDto);
	/* 게시물 등록 */
	int insertBoard(CreateBoardReqDto boardReqDto);
	/* 게시물 파일등록 */
	int insertBoardImg(CreateBoardImgReqDto boardImgDto);
	/* 게시물 상세*/
	ReadBoardDtlResDto getBoardDtl(ReadBoardDtlReqDto boardReqDto);
	/* 게시물 상세 이미지 조회 */
	List<CreateBoardImgReqDto> getBoardDtlImg(ReadBoardDtlReqDto boardReqDto);
	/* 게시물 수정 & 삭제 */
	int updateBoard(UpdateBoardReqDto updateBoardReqDto);

	List<ReadReplyResDto> getReply(int boardId);

	int insertReply(CreateReplyReqDto replyReqDto);

    int updateReply(UpdateReplyReqDto replyReqDto);
}
