package com.pjt.core.example.dto;

import com.pjt.core.example.entity.BookInventory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class CreateBookInventoryRequest {

	@Schema(description = "책 코드")
	private String bookCode;

	@Schema(description = "재고")
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
