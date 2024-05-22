package com.pjt.core.board.controller;

import java.io.IOException;
import java.util.List;


import com.pjt.core.board.dto.ReplyRequestDto;
import org.springframework.web.bind.annotation.*;
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

	@PostMapping("/updateBoard")
	public String updateBoard(@Valid @RequestPart("dto") CreateBoardRequestDto dto,
							  @RequestPart(value = "files", required=false) List<MultipartFile> files,
							  @RequestPart(value = "deleteImgNo", required = false) List<String> deleteImgNo) throws IOException {
		return boardService.updateBoard(dto, files, deleteImgNo);
	}

	@PostMapping("/reply")
	public String createReply(@Valid @RequestBody ReplyRequestDto dto) {
		return boardService.createReply(dto);
	}


	
	

	

}
