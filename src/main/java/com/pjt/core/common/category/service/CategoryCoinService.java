package com.pjt.core.common.category.service;

import com.pjt.core.common.category.dto.CreateCategoryCoinRequest;
import com.pjt.core.common.category.dto.UpdateCategoryCoinRequest;
import com.pjt.core.common.category.entity.CategoryCoin;
import com.pjt.core.common.category.exception.CategoryCoinException;
import com.pjt.core.common.category.repository.CategoryCoinRepository;
import com.pjt.core.common.code.service.CommonService;
import com.pjt.core.common.error.response.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryCoinService {

	private final CommonService commonService;
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
		// 공통코드 카테고리 조회
		long count = commonService.getCommonCode(category).stream().count();

		if (count == 0) {
			throw new CategoryCoinException(ErrorCode.NOT_FOUND_CATEGORY);
		}

		// 카테코리별 코인 조회
		CategoryCoin categoryCoin = categoryCoinRepository.findById(category)
				.orElseThrow(() -> new CategoryCoinException(ErrorCode.NOT_FOUND_CATEGORY));

		return categoryCoin.getCoin();
	}

	/**
	 * 카테고리별 코인 등록
	 * @param request 카테고리별 코인 생성
	 */
	public void createCategoryCoin(CreateCategoryCoinRequest request) {
		CategoryCoin categoryCoin = CreateCategoryCoinRequest.toEntity(request);

		categoryCoinRepository.save(categoryCoin);
	}

	/**
	 * 카테고리별 코인 수정
	 * @param category 카테고리
	 * @param request 카테고리별 코인 수정
	 */
	public void updateCategoryCoin(String category, UpdateCategoryCoinRequest request) {
		CategoryCoin categoryCoin = categoryCoinRepository.findById(category)
				.orElseThrow(() -> new CategoryCoinException(ErrorCode.NOT_FOUND_CATEGORY));

		categoryCoin.updateCoin(request.getCoin());
	}

}
