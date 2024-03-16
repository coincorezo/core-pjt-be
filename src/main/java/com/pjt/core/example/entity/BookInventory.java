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
public class BookInventory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Comment("책코드")
	private String bookCode;

	@Comment("수량")
	private int quantity;

	@Comment("등록시간")
	private LocalDateTime registeredDateTime;

	@Comment("수정시간")
	private LocalDateTime updateDateTime;

	public void checkValidInventoryQuantity(int quantity) {
		if (quantity < 0) {
			throw new IllegalArgumentException("유효한 재고 수량이 아닙니다.");
		}
	}

}
