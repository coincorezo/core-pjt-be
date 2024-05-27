package com.pjt.core.common.category.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class CategoryCoin {

	@Id
	private String categoryCode;

	private String categoryName;

	private int coin;

	public void updateCoin(int coin) {
		this.coin = coin;
	}

}
