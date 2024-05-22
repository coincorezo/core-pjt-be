package com.pjt.core.common.category.controller;

import com.pjt.core.common.ApiResponse;
import com.pjt.core.common.category.dto.CreateCategoryCoinRequest;
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

	@GetMapping("/category-coin")
	public ApiResponse<List<CategoryCoin>> getCategoryCoin() {
		List<CategoryCoin> categoryCoinList = categoryCoinService.getCategoryCoin();

		return ApiResponse.ok(categoryCoinList);
	}

	@PostMapping("/category-coin")
	public ApiResponse<Void> createCategoryCoin(@RequestBody CreateCategoryCoinRequest request) {
		categoryCoinService.createCategoryCoin(request);

		return ApiResponse.ok(null);
	}

}
