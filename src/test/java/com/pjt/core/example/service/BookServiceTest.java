package com.pjt.core.example.service;

import com.pjt.core.example.dto.CreateBookRequest;
import com.pjt.core.example.dto.CreateBookResponse;
import com.pjt.core.example.repository.BookRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
class BookServiceTest {
	
	@Autowired
	private BookService bookService;

	@Autowired
	private BookRepository bookRepository;

	@AfterEach
	void tearDown() {
		bookRepository.deleteAllInBatch();
	}

	@DisplayName("책의 제목, 부제, 글쓴이를 입력받아 책을 등록한다.")
	@Test
	void registerBook() {
		// given
		LocalDateTime registeredDateTime = LocalDateTime.now();

		String title = "객체지향의 사실과 오해";
		String subtitle = "역할, 책임, 협력 관점에서 본 객체지향";
		String writer = "조영호";
		CreateBookRequest createBookRequest = CreateBookRequest.builder()
			.title(title)
			.subtitle(subtitle)
			.writer(writer)
			.build();
		
		// when
		CreateBookResponse createBookResponse = bookService.registerBook(createBookRequest, registeredDateTime);
		
		// then
		assertThat(createBookResponse.getId()).isNotNull();
		assertThat(createBookResponse)
				.extracting("title", "subtitle", "writer", "registeredDateTime")
				.contains(title, subtitle, writer, registeredDateTime);
	}

}