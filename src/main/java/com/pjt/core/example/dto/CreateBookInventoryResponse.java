package com.pjt.core.example.dto;

import com.pjt.core.example.entity.BookInventory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class CreateBookInventoryResponse {

	@Schema(description = "책 재고 id")
	private Long id;

	@Schema(description = "책 코드")
	private String bookCode;

	@Schema(description = "재고")
	private int quantity;

	@Schema(description = "등록시간")
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
