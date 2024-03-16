package com.pjt.core.example.dto;

import com.pjt.core.example.entity.BookInventory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class CreateBookInventoryResponse {

	private Long id;

	private String bookCode;

	private int quantity;

	private LocalDateTime registeredDateTime;

	public static CreateBookInventoryResponse fromEntity(BookInventory bookInventory) {
		return CreateBookInventoryResponse.builder()
				.id(bookInventory.getId())
				.bookCode(bookInventory.getBookCode())
				.quantity(bookInventory.getQuantity())
				.registeredDateTime(bookInventory.getRegisteredDateTime())
				.build();
	}

}
