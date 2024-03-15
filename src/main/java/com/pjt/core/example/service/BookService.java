package com.pjt.core.example.service;

import com.pjt.core.example.dto.CreateBookRequest;
import com.pjt.core.example.dto.CreateBookResponse;
import com.pjt.core.example.dto.CreateBookInventoryRequest;
import com.pjt.core.example.dto.CreateBookInventoryResponse;
import com.pjt.core.example.entity.Book;
import com.pjt.core.example.entity.BookInventory;
import com.pjt.core.example.repository.BookInventoryRepository;
import com.pjt.core.example.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BookService {

	private final BookRepository bookRepository;

	private final BookInventoryRepository bookInventoryRepository;

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

	/**
	 * 등록된 책의 재고 생성
	 * @param request
	 * @param registeredDateTime
	 * @return
	 */
	@Transactional
	public CreateBookInventoryResponse registerBookInventory(CreateBookInventoryRequest request, LocalDateTime registeredDateTime) {
		BookInventory bookInventory = CreateBookInventoryRequest.toEntity(request, registeredDateTime);
		bookInventory.checkValidInventoryQuantity(bookInventory.getQuantity());
		BookInventory savedBookInventory = bookInventoryRepository.save(bookInventory);

		return CreateBookInventoryResponse.fromEntity(savedBookInventory);
	}

}
