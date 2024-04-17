package com.pjt.core.example.dto;

import com.pjt.core.example.entity.Book;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class CreateBookResponse {

	@Schema(description = "책 id")
	private Long id;

	@Schema(description = "책 코드")
	private String bookCode;

	@Schema(description = "책 코드")
	private String title;

	@Schema(description = "책 부제")
	private String subtitle;

	@Schema(description = "글쓴이")
	private String writer;

	@Schema(description = "등록시간")
	private LocalDateTime registeredDateTime;

	public static CreateBookResponse fromEntity(Book book) {
		return CreateBookResponse.builder()
				.id(book.getId())
				.bookCode(book.getBookCode())
				.title(book.getTitle())
				.subtitle(book.getSubtitle())
				.writer(book.getWriter())
				.registeredDateTime(book.getRegisteredDateTime())
				.build();
	}

}
