package com.pjt.core.common.category.controller;

import com.pjt.core.common.ApiResponse;
import com.pjt.core.common.category.dto.CreateCategoryCoinRequest;
import com.pjt.core.common.category.dto.UpdateCategoryCoinRequest;
import com.pjt.core.common.category.entity.CategoryCoin;
import com.pjt.core.common.category.service.CategoryCoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CategoryCoinController {

	private final CategoryCoinService categoryCoinService;

	/**
	 * 카테고리별 코인 목록 조회 API
	 * @return 카테고리별 코인 목록
	 */
	@GetMapping("/category-coin")
	public ApiResponse<List<CategoryCoin>> getCategoryCoin() {
		List<CategoryCoin> categoryCoinList = categoryCoinService.getCategoryCoin();

		return ApiResponse.ok(categoryCoinList);
	}

	/**
	 * 카테고리별 코인 등록 API
	 * @param request 카테고리별 코인 생성
	 * @return 카테고리별 코인 등록 결과
	 */
	@PostMapping("/category-coin")
	public ApiResponse<Void> createCategoryCoin(@RequestBody CreateCategoryCoinRequest request) {
		categoryCoinService.createCategoryCoin(request);

		return ApiResponse.ok(null);
	}

	/**
	 * 카테고리별 코인 수정 API
	 * @param category 카테고리
	 * @param request 카테고리별 코인 수정
	 * @return 카테고리별 코인 수정 결과
	 */
	@PatchMapping("/category-coin/{category}")
	public ApiResponse<Void> updateCategoryCoin(
			@PathVariable String category,
			@RequestBody UpdateCategoryCoinRequest request
	) {
		categoryCoinService.updateCategoryCoin(category, request);

		return ApiResponse.ok(null);
	}

}
