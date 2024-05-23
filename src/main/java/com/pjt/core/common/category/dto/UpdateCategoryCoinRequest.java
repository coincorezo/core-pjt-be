package com.pjt.core.common.category.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class UpdateCategoryCoinRequest {

	@NotNull(message = "코인을 입력해주세요.")
	private int coin;

}
