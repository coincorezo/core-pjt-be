package com.pjt.core.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pjt.core.board.dto.BoardRequestDto;
import com.pjt.core.board.service.BoardService;
import com.pjt.core.common.util.ApiResponse;
import com.pjt.core.common.util.PagingResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class TestController {
	
	@Autowired
	private final BoardService boardService;

	
	@GetMapping("/")
	public String board() {
		return "/test2";
	}
	
	@GetMapping("/board")
	@ResponseBody
	public ApiResponse<Object> getBoard(@Valid BoardRequestDto boardRequestDto) {
		ApiResponse<Object> data = boardService.getBoard(boardRequestDto);

		return data;
	}
	
	@GetMapping("/test")
	public String test() {
		return "/test2";
	}
}
