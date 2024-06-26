package com.pjt.core.board.controller;

import java.io.IOException;
import java.util.List;


import com.pjt.core.board.dto.ReadBoardRequestDto;
import com.pjt.core.board.dto.ReplyRequestDto;
import com.pjt.core.board.dto.boardJ.DeleteReplyRequestDto;
import com.pjt.core.board.dto.boardJ.UpdateReplyRequestDto;
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
	public List<ReadBoardResponseDto> getBoard(ReadBoardRequestDto dto) {
		return boardJService.getBoard(dto);
	}

	@GetMapping("/detail/{boardId}")
	public ReadBoardResponseDto getDetail(@Valid @PathVariable(value="boardId")String boardId) throws Exception {
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

	@PatchMapping("/reply")
	public String updateReply(@RequestBody UpdateReplyRequestDto dto) {
		return boardJService.updateReply(dto);
	}

	@PostMapping("/reply/deleteReply")
	public String deleteReply(@RequestBody DeleteReplyRequestDto dto) {
		return boardJService.deleteReply(dto);
	}


	
	

	

}
