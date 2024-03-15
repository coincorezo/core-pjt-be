package com.pjt.core.example.service;

import com.pjt.core.example.dto.CreateBookRequest;
import com.pjt.core.example.dto.CreateBookResponse;
import com.pjt.core.example.entity.Book;
import com.pjt.core.example.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BookService {

	private final BookRepository bookRepository;

	/**
	 * 책 등록하기
	 * @param request
	 * @param registeredDateTime
	 * @return
	 */
	@Transactional
	public CreateBookResponse registerBook(CreateBookRequest request, LocalDateTime registeredDateTime) {
		Book book = CreateBookRequest.toEntity(request, registeredDateTime);
		Book savedBook = bookRepository.save(book);

		return CreateBookResponse.fromEntity(savedBook);
	}

}
