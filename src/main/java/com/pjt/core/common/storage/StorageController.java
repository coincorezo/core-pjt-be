package com.pjt.core.common.storage;

import com.pjt.core.common.error.response.EmptyDto;
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
public class StorageController {

	private final StorageService storageService;

	@PostMapping("/api/upload")
	public ResponseEntity<EmptyDto> upload(
			@RequestParam(value = "file") MultipartFile file
	) {
		storageService.store(file);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping("/api/upload/{filename}")
	public ResponseEntity<Resource> upload(
			@PathVariable String filename
	) {
		Resource file = storageService.loadAsResource(filename);
		String encodedFilename = URLEncoder.encode(file.getFilename(), StandardCharsets.UTF_8);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=\"" + encodedFilename + "\"").body(file);
	}

}
