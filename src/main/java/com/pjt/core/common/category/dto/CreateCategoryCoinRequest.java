package com.pjt.core.common.category.dto;

import com.pjt.core.common.category.entity.CategoryCoin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class CreateCategoryCoinRequest {

	@NotBlank(message = "카테고리를 입력해주세요.")
	private String category;

	@NotNull(message = "코인을 입력해주세요.")
	private int coin;

	public static CategoryCoin toEntity(CreateCategoryCoinRequest request) {
		return CategoryCoin.builder()
				.category(request.getCategory())
				.coin(request.getCoin())
				.build();
	}

}
