package com.pjt.core.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Comment("책코드")
	private String bookCode;

	@Comment("제목")
	private String title;

	@Comment("부제")
	private String subtitle;

	@Comment("글쓴이")
	private String writer;

	@Comment("등록시간")
	private LocalDateTime registeredDateTime;

}
