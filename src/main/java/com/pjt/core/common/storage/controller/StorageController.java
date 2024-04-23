package com.pjt.core.common.storage.controller;

import com.pjt.core.common.storage.dto.StorageResponse;
import com.pjt.core.common.storage.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/upload")
public class StorageController {

	private final StorageService storageService;

	/**
	 * 파일 업로드
	 * @param file 파일
	 * @return 업로드 파일 정보
	 */
	@PostMapping
	public ResponseEntity<StorageResponse> upload(
			@RequestParam(value = "file") MultipartFile file
	) {
		StorageResponse storageResponse = storageService.store(file);
		return new ResponseEntity<>(storageResponse, HttpStatus.CREATED);
	}

	/**
	 * 파일 다운로드
	 * @param filename 파일명
	 * @return 파일
	 */
	@GetMapping("/{filename}")
	public ResponseEntity<Resource> getFileByFilename(
			@PathVariable(value = "filename") String filename
	) {
		Resource file = storageService.loadAsResource(filename);
		String encodedFilename = URLEncoder.encode(file.getFilename(), StandardCharsets.UTF_8);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=\"" + encodedFilename + "\"").body(file);
	}

	/**
	 * 파일 삭제
	 * @param filename 파일명
	 * @return 삭제 결과
	 */
	@DeleteMapping("/{filename}")
	public ResponseEntity<Void> deleteByFilename(
			@PathVariable(value = "filename") String filename
	) {
		storageService.delete(filename);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
