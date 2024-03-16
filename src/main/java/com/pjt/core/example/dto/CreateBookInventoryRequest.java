package com.pjt.core.example.dto;

import com.pjt.core.example.entity.BookInventory;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class CreateBookInventoryRequest {

	private String bookCode;

	private int quantity;

	public static BookInventory toEntity(CreateBookInventoryRequest request, LocalDateTime registeredDateTime) {
		return BookInventory.builder()
				.bookCode(request.getBookCode())
				.quantity(request.getQuantity())
				.registeredDateTime(registeredDateTime)
				.updateDateTime(registeredDateTime)
				.build();
	}

}
