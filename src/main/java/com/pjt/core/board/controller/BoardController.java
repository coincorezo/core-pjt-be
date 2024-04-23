package com.pjt.core.board.controller;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pjt.core.board.dto.CreateBoardRequestDto;
import com.pjt.core.board.dto.ReadBoardResponseDto;
import com.pjt.core.board.service.BoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")

public class BoardController {
	
	private final BoardService boardService;
	
	@Transactional
	@GetMapping("/board")
	public List<ReadBoardResponseDto> getBoard(@RequestParam(value="category") String category) {
		
		return boardService.getBoard(category);
	}
	
	@Transactional
	@GetMapping("/detail")
	public ReadBoardResponseDto getDetail(@RequestParam(value="boardId")String boardId) {
		return boardService.getDetail(boardId);
	}
	
	@Transactional
	@PostMapping("/board")
	public String createBoard(@RequestBody CreateBoardRequestDto dto, List<MultipartFile> files) {
		String message = boardService.createBoard(dto, files);
		return null;
	}
	

}
