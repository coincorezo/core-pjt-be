package com.pjt.core.board;

import java.util.ArrayList;
import java.util.List;

import com.pjt.core.board.dto.*;
import com.pjt.core.board.exception.BoardException;
import com.pjt.core.common.ApiResponse;
import com.pjt.core.common.category.service.CategoryCoinService;
import com.pjt.core.common.error.exception.NoDataException;
import com.pjt.core.common.error.response.ErrorCode;
import com.pjt.core.user.dto.CurrentUser;
import com.pjt.core.user.service.UserService;
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
    private UserService userService;
    @Autowired
    private CategoryCoinService categoryCoinService;

    /**
     * <pre>
     * 게시판 리스트
     * </pre>
     *
     * @param boardReqDto
     * @return BoardDto
     */
    public List<BoardDto> getBoardList(ReadBoardListReqDto boardReqDto) {

        return boardMapper.getBoardList(boardReqDto);
    }

    /**
     * <pre>
     * 게시판 등록
     * </pre>
     *
     * @param boardReqDto
     * @return CreateBoardResDto
     */
    public CreateBoardResDto insertBoard(CreateBoardReqDto boardReqDto
            /*,MultipartHttpServletRequest multiRequest*/) throws Exception {
        //todo : 아이디, 코인 적립 보드 등록할 때
        //프론트에서 보내주는 값이
        //카테고리랑
        //코인 값 맞는지
        //값만 확인 하면 되는거죠?
        //CategoryCoinService.getCategoryCoinByCategory(String category)
        //코인 적립 서비스 가져오기
        //id비교 하기

        int coin = categoryCoinService.getCategoryCoinByCategory(boardReqDto.getCategory());
        if (coin != boardReqDto.getCoin()) {
            throw new BoardException(ErrorCode.NOT_COIN);
        }
        CreateBoardResDto boardResDto = new CreateBoardResDto();
        /*insert*/
        int saveCount = boardMapper.insertBoard(boardReqDto);
        if (saveCount == 0) {
            throw new BoardException(ErrorCode.NOT_SAVE);

        }
        boardResDto.setBoardId(boardReqDto.getBoardId());


        return boardResDto;
    }

    /**
     * <pre>
     * 게시판 상세
     * </pre>
     *
     * @param boardReqDto
     * @return ReadBoardDtlResDto
     */
    public ReadBoardDtlResDto getBoardDtl(ReadBoardDtlReqDto boardReqDto) {
        //todo : 상세보기 count
        ReadBoardDtlResDto boardResDto = new ReadBoardDtlResDto();
        boardResDto = boardMapper.getBoardDtl(boardReqDto);

        List<CreateBoardImgReqDto> boardImgDtoList = new ArrayList<CreateBoardImgReqDto>();

        boardImgDtoList = boardMapper.getBoardDtlImg(boardReqDto);
        if (!boardImgDtoList.isEmpty()) {
            boardResDto.setBoardImgdto(boardImgDtoList);
        }
        List<ReadReplyResDto> replyList = this.getReplyList(boardReqDto.getBoardId());
        if (!replyList.isEmpty()) {
            boardResDto.setReplyList(replyList);
        }
        return boardResDto;
    }

    /**
     * <pre>
     * 게시판 수정
     * </pre>
     *
     * @param updateBoardReqDto
     * @return CreateBoardResDto
     */
    public CreateBoardResDto updateBoard(UpdateBoardReqDto updateBoardReqDto) {
        //todo : 로그인정보 맞는 확인 후 수정

        /* 게시글 여부 확인*/
        this.checkValid(updateBoardReqDto.getBoardId());

        int saveCount = 0;
        saveCount = boardMapper.updateBoard(updateBoardReqDto);

        CreateBoardResDto updateBoard = new CreateBoardResDto();
        if (saveCount > 0) {
            updateBoard.setBoardId(updateBoardReqDto.getBoardId());
        } else {
            throw new BoardException(ErrorCode.NOT_SAVE);
        }
        return updateBoard;
    }

    /**
     * <pre>
     * 게시판 삭제(delete)
     * </pre>
     *
     * @param updateBoardReqDto
     * @return CreateBoardResDto
     */
    public CreateBoardResDto deleteBoard(UpdateBoardReqDto updateBoardReqDto) {
        //todo : 로그인정보 맞는 확인 후  삭제
        CurrentUser currentUser = userService.getLoginUser();
        //if(id === id) {}
        this.checkValid(updateBoardReqDto.getBoardId());
        /* 게시글 여부 확인 후 boardStatus DELETE 변경*/
        CreateBoardResDto updateBoard = new CreateBoardResDto();

        int saveCount = boardMapper.updateBoard(updateBoardReqDto);
        updateBoard.setBoardId(updateBoardReqDto.getBoardId());
        if (saveCount == 0) {
            throw new BoardException(ErrorCode.NO_DATA);
        }
        return updateBoard;
    }

    /**
     * <pre>
     * 게시판 댓글 리스트
     * </pre>
     *
     * @param boardId
     * @return ReadReplyResDto
     */
    public List<ReadReplyResDto> getReplyList(int boardId) {

        return boardMapper.getReply(boardId);
    }

    /**
     * <pre>
     * 게시판 댓글 등록
     * </pre>
     *
     * @param replyReqDto
     * @return String
     */
    public CreateBoardResDto insertReply(CreateReplyReqDto replyReqDto) {
        // todo : 등록자 id정보 받아오기
        /* 게시글 여부 확인 */
        ReadBoardDtlResDto readBoardDtlResDto = this.checkValid(replyReqDto.getBoardId());

        /*게시글 있으면 댓글 등록 */
        checkStatus(readBoardDtlResDto);

        int saveCount = boardMapper.insertReply(replyReqDto);
        CreateBoardResDto createBoardResDto = new CreateBoardResDto();
        createBoardResDto.setBoardId(replyReqDto.getBoardId());

        return createBoardResDto;
    }

    /*<pre>
     *게시판 댓글 수정
     * </pre>
     * @author KangMinJi
     * @return CreateBoardResDto
     * @param replyReqDto
     */
    public CreateBoardResDto updateReply(UpdateReplyReqDto replyReqDto) {
        // todo : 등록자 id정보 맞는지 확인 후 수정 & 삭제

        /* 게시글 여부 확인 */
        ReadBoardDtlResDto readBoardDtlResDto = this.checkValid(replyReqDto.getBoardId());

        /* 게시글 있으면 댓글  삭제 || 수정 */
        checkStatus(readBoardDtlResDto);

        int saveCount = boardMapper.updateReply(replyReqDto);
        if (saveCount == 0) {
            throw new BoardException(ErrorCode.NOT_SAVE);
        }

        CreateBoardResDto resDto = new CreateBoardResDto();
        resDto.setBoardId(replyReqDto.getBoardId());

        return resDto;
    }

    /*
     * <pre>
     * 댓글 등록시 public만 등록할 수 있도록 체크
     * </pre>
     * @author KangMinJi
     * @param readBoardDtlResDto
     */
    private void checkStatus(ReadBoardDtlResDto readBoardDtlResDto) {
        if (!("PUBLIC").equals(readBoardDtlResDto.getBoardStatus())) {
            throw new BoardException(ErrorCode.NOT_SAVE);
        }
    }

    /*
     * <pre>
     * 댓글 등록시 게시글이 있는지 확인 여부
     * </pre>
     * @author KangMinJi
     * @param readBoardDtlResDto
     */
    public ReadBoardDtlResDto checkValid(int boardId) {

        /* 게시글 여부 확인 */
        ReadBoardDtlReqDto readBoardDtlReqDto = new ReadBoardDtlReqDto();
        readBoardDtlReqDto.setBoardId(boardId);
        ReadBoardDtlResDto readBoardDtlResDto = this.getBoardDtl(readBoardDtlReqDto);
        String status = readBoardDtlResDto.getBoardStatus();

        if (readBoardDtlResDto == null || ("DELETE").equals(status)) {
            throw new BoardException(ErrorCode.NO_DATA);
        }

        return readBoardDtlResDto;
    }
}
