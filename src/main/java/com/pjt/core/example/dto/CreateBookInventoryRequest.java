package com.pjt.core.example.dto;

import com.pjt.core.example.entity.BookInventory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class CreateBookInventoryRequest {

	private Long bookCode;

	private int quantity;

	private LocalDateTime registeredDateTime;

	private LocalDateTime updateDateTime;

	public static BookInventory toEntity(CreateBookInventoryRequest request, LocalDateTime registeredDateTime) {
		return BookInventory.builder()
				.bookCode(request.getBookCode())
				.quantity(request.getQuantity())
				.registeredDateTime(registeredDateTime)
				.updateDateTime(registeredDateTime)
				.build();
	}

}
