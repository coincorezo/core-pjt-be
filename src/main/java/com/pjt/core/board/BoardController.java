package com.pjt.core.board;

import java.util.List;

import com.pjt.core.board.dto.*;
import com.pjt.core.common.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api")
@Tag(name = "BoardController", description = "게시판 API")
public class BoardController {

    @Autowired
    BoardService boardService;

    /**
     * <pre>
     * 게시판 리스트
     * </pre>
     *
     * @param boardReqDto
     * @return
     * @author KangMinJi (kmj0701@coremethod.co.kr)
     */
    @GetMapping("/board")
    @Operation(summary = "게시판 조회", description = "게시판 리스트가 조회됩니다.")
    public List<BoardDto> getBoardList(ReadBoardListReqDto boardReqDto) {
        return boardService.getBoardList(boardReqDto);
    }

    /**
     * <pre>
     * 게시판 등록
     * </pre>
     *
     * @param boardReqDto
     * @return CreateBoardResDto
     * @author KangMinJi (kmj0701@coremethod.co.kr)
     */
    @PostMapping("/board")
    @Operation(summary = "게시판 등록", description = "게시판 등록합니다.")
    //@RequestMapping(value="/", method = {RequestMethod.POST})
    public ApiResponse<CreateBoardResDto> insertBoard(@RequestBody @Valid CreateBoardReqDto boardReqDto)
            throws Exception {

        CreateBoardResDto createBoardResDto = boardService.insertBoard(boardReqDto);

        return ApiResponse.ok(createBoardResDto);
    }


    /**
     * <pre>
     * 게시판 상세
     * </pre>
     *
     * @param boardReqDto
     * @return ReadBoardDtlResDto
     * @author KangMinJi (kmj0701@coremethod.co.kr)
     */
    @GetMapping("/board/{boardId}")
    @Operation(summary = "게시판 상세 조회", description = "게시판 상세 조회됩니다.")
    public ApiResponse<ReadBoardDtlResDto> getBoard(/*@RequestParam(value="boardId", required=true) int boardId , */ReadBoardDtlReqDto boardReqDto, @PathVariable(value = "boardId") Integer boardId) {

        ReadBoardDtlResDto readBoardDtlResDto = boardService.getBoardDtl(boardReqDto);

        return ApiResponse.ok(readBoardDtlResDto);
    }

    /**
     * <pre>
     * 게시판 수정
     * </pre>
     *
     * @param updateBoardReqDto
     * @return CreateBoardResDto
     * @author KangMinJi (kmj0701@coremethod.co.kr)
     */
    @PutMapping("/board")
    @Operation(summary = "게시판 수정", description = "게시판 수정이 됩니다")
    public ApiResponse<CreateBoardResDto> updateBoard(@RequestBody UpdateBoardReqDto updateBoardReqDto) {
        //응용
        CreateBoardResDto createBoardResDto = boardService.updateBoard(updateBoardReqDto);

        return ApiResponse.ok(createBoardResDto);
    }

    /**
     * <pre>
     * 게시판 삭제
     * </pre>
     *
     * @param updateBoardReqDto
     * @return CreateBoardResDto
     * @author KangMinJi (kmj0701@coremethod.co.kr)
     */
    @DeleteMapping("/board")
    @Operation(summary = "게시판 삭제", description = "게시판 삭제가 됩니다")
    public ApiResponse<CreateBoardResDto> deleteBoard(@RequestBody UpdateBoardReqDto updateBoardReqDto) {

        CreateBoardResDto createBoardResDto = boardService.deleteBoard(updateBoardReqDto);

        return ApiResponse.ok(createBoardResDto);
    }


    /*<pre>
     *게시판 댓글 등록
     * </pre>
     *
     * @author KangMinJi
     * @return CreateBoardResDto
     * @param CreateReplyReqDto
     */
    @PostMapping("/board/reply")
    @Operation(summary = "게시판 댓글 등록", description = "게시판 댓글 등록됩니다")
    public ApiResponse<CreateBoardResDto> insertReply(@RequestBody @Valid CreateReplyReqDto replyReqDto) {
        CreateBoardResDto createBoardResDto = boardService.insertReply(replyReqDto);
        return ApiResponse.ok(createBoardResDto);
    }

    /*<pre>
     *게시판 댓글 수정
     * </pre>
     *
     * @author KangMinJi
     * @param CreateReplyReqDto
     */
    @PutMapping("/board/reply")
    @Operation(summary = "게시판 댓글 등록", description = "게시판 댓글 수정됩니다")
    public ApiResponse<CreateBoardResDto> updateReply(@RequestBody @Valid UpdateReplyReqDto replyReqDto) {

        CreateBoardResDto createBoardResDto = boardService.updateReply(replyReqDto);
        return ApiResponse.ok(createBoardResDto);
    }


    /*<pre>
     *게시판 댓글 삭제
     * </pre>
     *
     * @author KangMinJi
     * @param CreateReplyReqDto
     */
    @DeleteMapping("/board/reply")
    @Operation(summary = "게시판 댓글 삭제", description = "게시판 댓글 삭제됩니다")
    public CreateBoardResDto deleteReply(@RequestBody @Valid UpdateReplyReqDto replyReqDto) {
        return boardService.updateReply(replyReqDto);
    }


}
