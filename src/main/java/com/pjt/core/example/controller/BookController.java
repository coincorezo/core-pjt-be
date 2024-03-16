package com.pjt.core.example.controller;

import com.pjt.core.example.dto.CreateBookInventoryRequest;
import com.pjt.core.example.dto.CreateBookInventoryResponse;
import com.pjt.core.example.dto.CreateBookRequest;
import com.pjt.core.example.dto.CreateBookResponse;
import com.pjt.core.example.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class BookController {

	private final BookService bookService;

	/**
	 * 책 등록 API
	 * @param request
	 * @return
	 */
	@PostMapping("/api/book")
	public ResponseEntity<CreateBookResponse> registerBook(@RequestBody CreateBookRequest request) {
		LocalDateTime registeredDateTime = LocalDateTime.now();
		CreateBookResponse response = bookService.registerBook(request, registeredDateTime);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	/**
	 * 책 재고 등록 API
	 * @param request
	 * @return
	 */
	@PostMapping("/api/book-invt")
	public ResponseEntity<CreateBookInventoryResponse> registerBookInventory(@RequestBody CreateBookInventoryRequest request) {
		LocalDateTime registeredDateTime = LocalDateTime.now();
		CreateBookInventoryResponse response = bookService.registerBookInventory(request, registeredDateTime);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

}
