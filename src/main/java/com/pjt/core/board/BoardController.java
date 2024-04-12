package com.pjt.core.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.pjt.core.board.dto.BoardDto;
import com.pjt.core.board.dto.CreateBoardReqDto;
import com.pjt.core.board.dto.ReadBoardReqDto;
import com.pjt.core.board.dto.ReadBoardResDto;

@RestController
@RequestMapping("api")
public class BoardController {

	@Autowired
	BoardService boardSevice;

	/**
	 * <pre>
	 * 게시판 리스트
	 * </pre>
	 *
	 * @author KangMinJi (kmj0701@coremethod.co.kr)
	 * @param CreateBoardReqDto
	 * @return
	 */
	@GetMapping("/board")
	// @Operation(description ="게시판 리스트")
	public List<BoardDto> getBoardList(CreateBoardReqDto boardReqDto) {
		return boardSevice.getBoardList(boardReqDto);
	}

	/**
	 * <pre>
	 * 게시판 업로드
	 * </pre>
	 *
	 * @author KangMinJi (kmj0701@coremethod.co.kr)
	 * @param ReadBoardReqDto
	 * @return
	 */
	@GetMapping("/board/insert")
	// @Operation(description ="게시판 리스트")
	public ReadBoardResDto insertBoard(/* @Validated */ReadBoardReqDto boardReqDto, MultipartHttpServletRequest request)
			throws Exception {
		return boardSevice.insertBoard(boardReqDto, request);
	}

}
