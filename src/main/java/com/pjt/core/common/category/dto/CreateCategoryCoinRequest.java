package com.pjt.core.common.category.dto;

import com.pjt.core.common.category.entity.CategoryCoin;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class CreateCategoryCoinRequest {

	private String categoryCode;

	private String categoryName;

	private int coin;

	public static CategoryCoin toEntity(CreateCategoryCoinRequest request) {
		return CategoryCoin.builder()
				.categoryCode(request.getCategoryCode())
				.categoryName(request.getCategoryName())
				.coin(request.getCoin())
				.build();
	}

}
