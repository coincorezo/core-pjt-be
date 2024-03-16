package com.pjt.core.example.dto;

import com.pjt.core.example.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class BookResponse {

	private Long id;

	private String bookCode;

	private String title;

	private String subtitle;

	private String writer;

	private LocalDateTime registeredDateTime;

	public static BookResponse fromEntity(Book book) {
		return BookResponse.builder()
				.id(book.getId())
				.bookCode(book.getBookCode())
				.title(book.getTitle())
				.subtitle(book.getSubtitle())
				.writer(book.getWriter())
				.registeredDateTime(book.getRegisteredDateTime())
				.build();
	}

}
