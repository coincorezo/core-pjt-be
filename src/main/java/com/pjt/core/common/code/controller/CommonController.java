package com.pjt.core.common.code.controller;

import com.pjt.core.common.code.dto.CreateCommonCodeRequest;
import com.pjt.core.common.code.dto.ReadCommonCodeResponse;
import com.pjt.core.common.code.service.CommonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/common")
public class CommonController {

	private final CommonService commonService;

	@GetMapping("/code")
	public ResponseEntity<List<ReadCommonCodeResponse>> getCommonCode(
			@RequestParam(value = "commonCode", required = false) String commonCode
	) {
		List<ReadCommonCodeResponse> response = commonService.getCommonCode(commonCode);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/code")
	public ResponseEntity<Void> createCommonCode(
			@Valid @RequestBody CreateCommonCodeRequest request
	) {
		commonService.createCommonCode(request);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}
