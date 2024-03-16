package com.pjt.core.example.dto;

import com.pjt.core.example.entity.Book;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class CreateBookRequest {

	private String bookCode;

	private String title;

	private String subtitle;

	private String writer;

	public static Book toEntity(CreateBookRequest request, LocalDateTime registeredDateTime) {
		return Book.builder()
				.bookCode(request.getBookCode())
				.title(request.getTitle())
				.subtitle(request.getSubtitle())
				.writer(request.getWriter())
				.registeredDateTime(registeredDateTime)
				.build();
	}

}
