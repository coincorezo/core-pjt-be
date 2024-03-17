package com.pjt.core.example.controller;

import com.pjt.core.example.dto.*;
import com.pjt.core.example.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
	 * 등록된 책 조회 API
	 * @param id
	 * @return
	 */
	@GetMapping("/api/book/{id}")
	public ResponseEntity<BookResponse> getBookById(@PathVariable Long id) {
		BookResponse response = bookService.getBookById(id);

		return new ResponseEntity<>(response, HttpStatus.OK);
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
