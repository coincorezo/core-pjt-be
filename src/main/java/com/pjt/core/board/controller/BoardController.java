package com.pjt.core.board.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pjt.core.board.dto.CreateBoardRequestDto;
import com.pjt.core.board.dto.ReadBoardResponseDto;
import com.pjt.core.board.service.BoardService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BoardController {
	
	private final BoardService boardService;
	

	@GetMapping("/board")
	public List<ReadBoardResponseDto> getBoard(@Valid @RequestParam(value="category") String category) {
		return boardService.getBoard(category);
	}

	@GetMapping("/detail")
	public ReadBoardResponseDto getDetail(@Valid @RequestParam(value="boardId")String boardId) throws Exception {
		return boardService.getDetail(boardId);
	}

	@PostMapping("/board")
	public String createBoard(@Valid @RequestPart("dto") CreateBoardRequestDto dto, @RequestPart(value="files", required=false) List<MultipartFile> files) throws Exception {
		return boardService.createBoard(dto, files);
	}
	
	@PostMapping("/board2")
	public String createBoard2(@Valid @RequestBody CreateBoardRequestDto dto) throws Exception {
		String data = boardService.test(dto);
		return data;
	}
	

	

}
