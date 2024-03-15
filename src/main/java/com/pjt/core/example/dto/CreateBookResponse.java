package com.pjt.core.example.dto;

import com.pjt.core.example.entity.Book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class CreateBookResponse {

	private Long id;

	private String title;

	private String subtitle;

	private String writer;

	private LocalDateTime registeredDateTime;

	public static CreateBookResponse fromEntity(Book book) {
		return CreateBookResponse.builder()
				.id(book.getId())
				.title(book.getTitle())
				.subtitle(book.getSubtitle())
				.writer(book.getWriter())
				.registeredDateTime(book.getRegisteredDateTime())
				.build();
	}

}
