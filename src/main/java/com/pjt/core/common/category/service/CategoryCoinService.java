package com.pjt.core.common.category.service;

import com.pjt.core.common.category.entity.CategoryCoin;
import com.pjt.core.common.category.repository.CategoryCoinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryCoinService {

	private final CategoryCoinRepository categoryCoinRepository;

	/**
	 * 카테고리별 코인 목록 조회
	 *
	 * @return 카테고리별 코인 목록
	 */
	public List<CategoryCoin> getCategoryCoin() {
		return categoryCoinRepository.findAll();
	}

	/**
	 * 카테고리별 코인 조회
	 *
	 * @param category 카테고리
	 * @return 카테고리별 코인
	 */
	public int getCategoryCoinByCategory(String category) {
		CategoryCoin categoryCoin = categoryCoinRepository.findById(category)
				.orElseThrow(() -> new RuntimeException("해당 카테고리가 존재하지 않습니다."));

		return categoryCoin.getCoin();
	}

}
