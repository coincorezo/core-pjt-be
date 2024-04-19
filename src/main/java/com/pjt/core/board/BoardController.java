package com.pjt.core.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.pjt.core.board.dto.BoardDto;
import com.pjt.core.board.dto.CreateBoardReqDto;
import com.pjt.core.board.dto.ReadBoardReqDto;
import com.pjt.core.board.dto.ReadBoardResDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api")
@Tag(name = "BoardController", description = "게시판 API")
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
	@Operation(summary = "게시판 조회", description = "게시판 리스트가 조회됩니다.")
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
	@Operation(summary = "게시판 등록", description = "게시판 등록합니다.")
	public ReadBoardResDto insertBoard(@Validated ReadBoardReqDto boardReqDto, MultipartHttpServletRequest request)
			throws Exception {
		return boardSevice.insertBoard(boardReqDto, request);
	}



	@GetMapping("/board/writer")
	//@Operation(summary = "게시판 등록", description = "게시판 등록합니다.")
	public String insertBoard2()
			throws Exception {
		return "writer";
	}

}
