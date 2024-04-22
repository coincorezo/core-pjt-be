package com.pjt.core.common.storage.controller;

import com.pjt.core.common.error.response.EmptyDto;
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

	@PostMapping
	public ResponseEntity<EmptyDto> upload(
			@RequestParam(value = "file") MultipartFile file
	) {
		storageService.store(file);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping("/{filename}")
	public ResponseEntity<Resource> getFileByFilename(
			@PathVariable(value = "filename") String filename
	) {
		Resource file = storageService.loadAsResource(filename);
		String encodedFilename = URLEncoder.encode(file.getFilename(), StandardCharsets.UTF_8);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=\"" + encodedFilename + "\"").body(file);
	}

	@DeleteMapping("/{filename}")
	public ResponseEntity<Void> deleteByFilename(
			@PathVariable(value = "filename") String filename
	) {
		storageService.delete(filename);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
