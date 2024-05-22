package com.pjt.core.board.controller;

import java.io.IOException;
import java.util.List;


import com.pjt.core.board.dto.ReplyRequestDto;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.pjt.core.board.dto.CreateBoardRequestDto;
import com.pjt.core.board.dto.ReadBoardResponseDto;
import com.pjt.core.board.service.BoardJService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api1")
public class BoardJController {
	
	private final BoardJService boardJService;
	

	@GetMapping("/board")
	public List<ReadBoardResponseDto> getBoard(@Valid @RequestParam(value="category") String category) {
		return boardJService.getBoard(category);
	}

	@GetMapping("/detail")
	public ReadBoardResponseDto getDetail(@Valid @RequestParam(value="boardId")String boardId) throws Exception {
		return boardJService.getDetail(boardId);
	}

	@PostMapping("/board")
	public String createBoard(@Valid @RequestPart("dto") CreateBoardRequestDto dto, @RequestPart(value="files", required=false) List<MultipartFile> files) throws Exception {
		return boardJService.createBoard(dto, files);
	}

	@PostMapping("/updateBoard")
	public String updateBoard(@Valid @RequestPart("dto") CreateBoardRequestDto dto,
							  @RequestPart(value = "files", required=false) List<MultipartFile> files,
							  @RequestPart(value = "deleteImgNo", required = false) List<String> deleteImgNo) throws IOException {
		return boardJService.updateBoard(dto, files, deleteImgNo);
	}

	@PostMapping("/reply")
	public String createReply(@Valid @RequestBody ReplyRequestDto dto) {
		return boardJService.createReply(dto);
	}


	
	

	

}
