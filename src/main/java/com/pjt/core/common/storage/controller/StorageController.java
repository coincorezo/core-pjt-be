package com.pjt.core.common.storage.controller;

import com.pjt.core.common.storage.dto.StorageResponse;
import com.pjt.core.common.storage.service.StorageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/upload")
@Tag(name = "StorageController", description = "파일 API")
public class StorageController {

	private final StorageService storageService;

	/**
	 * 파일 업로드
	 *
	 * @param file 파일
	 * @return 업로드 파일 정보
	 */
	@PostMapping(value = "/file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@Operation(summary = "파일 업로드", description = "파일 업로드")
	public ResponseEntity<StorageResponse> uploadFile(
			@Parameter(description = "multipart/form-data 형식의 이미지", content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE))
			@RequestPart(value = "file") MultipartFile file
	) {
		StorageResponse storageResponse = storageService.store(file);
		return new ResponseEntity<>(storageResponse, HttpStatus.CREATED);
	}

	/**
	 * 다중 파일 업로드
	 * @param files 파일 리스트
	 * @return 업로드 파일 정보 리스트
	 */
	@PostMapping(value = "/file-list", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@Operation(summary = "다중 파일 업로드", description = "다중 파일 업로드")
	public ResponseEntity<List<StorageResponse>> uploadFileList(
			@Parameter(description = "multipart/form-data 형식의 이미지", content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE))
			@RequestPart(value = "file") List<MultipartFile> files
	) {
		List<StorageResponse> storageResponse = storageService.storeAll(files);
		return new ResponseEntity<>(storageResponse, HttpStatus.CREATED);
	}

	/**
	 * 파일 다운로드
	 *
	 * @param filename 파일명
	 * @return 파일
	 */
	@GetMapping("/{filename}")
	@Operation(summary = "파일 다운로드", description = "파일 다운로드")
	public ResponseEntity<Resource> getFileByFilename(
			@Parameter(name = "filename", description = "저장된 파일명 (확장자 제외)", in = ParameterIn.PATH)
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
	@Operation(summary = "파일 삭제", description = "파일 삭제")
	public ResponseEntity<Void> deleteByFilename(
			@Parameter(name = "filename", description = "저장된 파일명 (확장자 제외)", in = ParameterIn.PATH)
			@PathVariable(value = "filename") String filename
	) {
		storageService.delete(filename);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
