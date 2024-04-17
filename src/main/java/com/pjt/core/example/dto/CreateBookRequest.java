package com.pjt.core.example.dto;

import com.pjt.core.example.entity.Book;

import io.swagger.v3.oas.annotations.media.Schema;
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

	@Schema(description = "책 코드")
	private String bookCode;

	@Schema(description = "책 제목")
	private String title;

	@Schema(description = "책 부제")
	private String subtitle;

	@Schema(description = "글쓴이")
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
