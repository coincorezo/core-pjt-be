package com.pjt.core.common.code.controller;

import com.pjt.core.common.code.dto.CreateCommonCodeRequest;
import com.pjt.core.common.code.dto.DeleteCommonCodeRequest;
import com.pjt.core.common.code.dto.ReadCommonCodeResponse;
import com.pjt.core.common.code.dto.UpdateCommonCodeRequest;
import com.pjt.core.common.code.service.CommonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/common")
@Tag(name = "CommonController", description = "공통 API")
public class CommonController {

	private final CommonService commonService;

	/**
	 * 공통코드 조회 API
	 * @param commonCode 공통코드
	 * @return 공통코드 목록
	 */
	@GetMapping("/code")
	@Operation(summary = "공통코드 조회", description = "공통코드를 조회합니다.")
	public ResponseEntity<List<ReadCommonCodeResponse>> getCommonCode(
			@Parameter(name = "commonCode", description = "공통코드")
			@RequestParam(value = "commonCode", required = false) String commonCode
	) {
		List<ReadCommonCodeResponse> response = commonService.getCommonCode(commonCode);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * 공통코드 등록 API
	 * @param request 공통코드 등록 DTO
	 * @return HTTP 상태코드
	 */
	@PostMapping("/code")
	@Operation(summary = "공통코드 등록", description = "공통코드를 등록합니다.")
	public ResponseEntity<Void> createCommonCode(
			@Valid @RequestBody CreateCommonCodeRequest request
	) {
		commonService.createCommonCode(request);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	/**
	 * 공통코드 수정 API
	 * @param request 공통코드 수정 DTO
	 * @return HTTP 상태코드
	 */
	@PutMapping("/code")
	@Operation(summary = "공통코드 수정", description = "공통코드를 수정합니다.")
	public ResponseEntity<Void> updateCommonCode(
			@Valid @RequestBody UpdateCommonCodeRequest request
	) {
		commonService.updateCommonCode(request);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * 공통코드 삭제 API
	 * @param request 공통코드 삭제 DTO
	 * @return HTTP 상태코드
	 */
	@DeleteMapping("/code")
	@Operation(summary = "공통코드 삭제", description = "공통코드를 삭제합니다.")
	public ResponseEntity<Void> deleteCommonCode(
			@Valid @RequestBody DeleteCommonCodeRequest request
	) {
		commonService.deleteCommonCode(request);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
