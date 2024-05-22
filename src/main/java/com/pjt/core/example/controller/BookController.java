package com.pjt.core.example.controller;

import com.pjt.core.example.dto.*;
import com.pjt.core.example.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@Tag(name = "BookController", description = "책 API")
public class BookController {

	private final BookService bookService;

	/**
	 * 책 등록 API
	 *
	 * @param request
	 * @return
	 */
	@PostMapping("/api/book")
	@Operation(summary = "책 정보 등록", description = "책 정보가 등록됩니다.")
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
	@Operation(summary = "등록된 책 조회", description = "등록된 책 정보를 조회합니다.")
	public ResponseEntity<BookResponse> getBookById(
			@Parameter(name = "id", description = "책 id", in = ParameterIn.PATH) @PathVariable Long id
	) {
		BookResponse response = bookService.getBookById(id);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * 책 재고 등록 API
	 * @param request
	 * @return
	 */
	@PostMapping("/api/book-invt")
	@Operation(summary = "책 재고 등록", description = "등록된 책 재고를 등록합니다.")
	public ResponseEntity<CreateBookInventoryResponse> registerBookInventory(@RequestBody CreateBookInventoryRequest request) {
		LocalDateTime registeredDateTime = LocalDateTime.now();
		CreateBookInventoryResponse response = bookService.registerBookInventory(request, registeredDateTime);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

}
