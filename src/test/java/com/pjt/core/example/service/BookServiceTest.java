package com.pjt.core.example.service;

import com.pjt.core.common.error.exception.NoDataException;
import com.pjt.core.common.error.response.ErrorCode;
import com.pjt.core.example.IntegrationTestSupport;
import com.pjt.core.example.dto.*;
import com.pjt.core.example.repository.BookInventoryRepository;
import com.pjt.core.example.repository.BookRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BookServiceTest extends IntegrationTestSupport {
	
	@Autowired
	private BookService bookService;

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private BookInventoryRepository bookInventoryRepository;

	@AfterEach
	void tearDown() {
		bookInventoryRepository.deleteAllInBatch();
		bookRepository.deleteAllInBatch();
	}

	@DisplayName("책의 제목, 부제, 글쓴이를 입력받아 책을 등록한다.")
	@Test
	void registerBook() {
		// given
		LocalDateTime registeredDateTime = LocalDateTime.now();

		String bookCode = "A00001";
		String title = "객체지향의 사실과 오해";
		String subtitle = "역할, 책임, 협력 관점에서 본 객체지향";
		String writer = "조영호";
		CreateBookRequest createBookRequest = CreateBookRequest.builder()
			.bookCode(bookCode)
			.title(title)
			.subtitle(subtitle)
			.writer(writer)
			.build();

		// when
		CreateBookResponse createBookResponse = bookService.registerBook(createBookRequest, registeredDateTime);

		// then
		assertThat(createBookResponse.getId()).isNotNull();
		assertThat(createBookResponse)
				.extracting("bookCode", "title", "subtitle", "writer", "registeredDateTime")
				.contains(bookCode, title, subtitle, writer, registeredDateTime);
	}

	@DisplayName("등록된 책의 재고를 생성한다.")
	@Test
	void registerBookInventory() {
	    // given
		// 책 등록
		LocalDateTime registeredDateTime = LocalDateTime.now();

		String bookCode = "A00001";
		String title = "객체지향의 사실과 오해";
		String subtitle = "역할, 책임, 협력 관점에서 본 객체지향";
		String writer = "조영호";
		CreateBookRequest createBookRequest = CreateBookRequest.builder()
				.bookCode(bookCode)
				.title(title)
				.subtitle(subtitle)
				.writer(writer)
				.build();

		CreateBookResponse createBookResponse = bookService.registerBook(createBookRequest, registeredDateTime);

		// when
		// 책 재고 등록
		int quantity = 10;
		CreateBookInventoryRequest createBookInventoryRequest = CreateBookInventoryRequest.builder()
				.bookCode(createBookResponse.getBookCode())
				.quantity(quantity)
				.build();

		CreateBookInventoryResponse createBookInventoryResponse = bookService.registerBookInventory(createBookInventoryRequest, registeredDateTime);

	    // then
		assertThat(createBookResponse.getId()).isNotNull();
		assertThat(createBookResponse)
				.extracting("bookCode", "title", "subtitle", "writer", "registeredDateTime")
				.contains(bookCode, title, subtitle, writer, registeredDateTime);
		assertThat(createBookInventoryResponse.getId()).isNotNull();
		assertThat(createBookInventoryResponse)
				.extracting("bookCode", "quantity", "registeredDateTime")
				.contains(createBookResponse.getBookCode(), quantity, registeredDateTime);
	}

	@DisplayName("등록된 책을 조회한다.")
	@Test
	void getBookById() {
	    // given
		// 책 등록
		LocalDateTime registeredDateTime = LocalDateTime.now();

		String bookCode = "A00001";
		String title = "객체지향의 사실과 오해";
		String subtitle = "역할, 책임, 협력 관점에서 본 객체지향";
		String writer = "조영호";
		CreateBookRequest createBookRequest = CreateBookRequest.builder()
				.bookCode(bookCode)
				.title(title)
				.subtitle(subtitle)
				.writer(writer)
				.build();

	    // when
		CreateBookResponse createBookResponse = bookService.registerBook(createBookRequest, registeredDateTime);
		BookResponse bookResponse = bookService.getBookById(createBookResponse.getId());

		// then
		assertThat(createBookResponse.getId()).isNotNull();
		assertThat(createBookResponse)
				.extracting("bookCode", "title", "subtitle", "writer", "registeredDateTime")
				.contains(bookCode, title, subtitle, writer, registeredDateTime);
		assertThat(bookResponse.getId()).isNotNull();
		assertThat(bookResponse)
				.extracting("bookCode", "title", "subtitle", "writer", "registeredDateTime")
				.contains(bookCode, title, subtitle, writer, registeredDateTime);
	}

	@DisplayName("등록되지 않은 책을 조회할 시 예외를 발생시킨다.")
	@Test
	void getEmptyBookById() {
		// given
		// 책 등록
		LocalDateTime registeredDateTime = LocalDateTime.now();

		String bookCode = "A00001";
		String title = "객체지향의 사실과 오해";
		String subtitle = "역할, 책임, 협력 관점에서 본 객체지향";
		String writer = "조영호";
		CreateBookRequest createBookRequest = CreateBookRequest.builder()
				.bookCode(bookCode)
				.title(title)
				.subtitle(subtitle)
				.writer(writer)
				.build();

		// when
		bookService.registerBook(createBookRequest, registeredDateTime);

		// then
		assertThatThrownBy(() -> {
			bookService.getBookById(529L);
		})
				.isExactlyInstanceOf(NoDataException.class)
				.hasMessage(ErrorCode.NO_DATA.getMessage());
	}

}