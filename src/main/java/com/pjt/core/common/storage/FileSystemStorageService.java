package com.pjt.core.common.storage;

import com.pjt.core.common.error.exception.StorageException;
import com.pjt.core.common.error.response.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileSystemStorageService implements StorageService {

	private final Path rootLocation;

	@Autowired
	public FileSystemStorageService(StorageProperties properties) {
		if (properties.getLocation().trim().isEmpty()) {
			throw new StorageException(ErrorCode.INTERNAL_SERVER_ERROR);
		}

		// 파일업로드 경로 설정 초기화
		rootLocation = Paths.get(properties.getLocation()).normalize().toAbsolutePath();
		init();
	}

	@Override
	public void init() {
		try {
			// 디렉토리 생성
			Files.createDirectories(rootLocation);
		} catch (IOException e) {
			throw new StorageException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public void store(MultipartFile file) {
		// 빈 파일 체크
		if (file.isEmpty()) {
			throw new StorageException(ErrorCode.INTERNAL_SERVER_ERROR);
		}

		// 파일 정보
		String originalFilename = file.getOriginalFilename();
		String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);

		// 저장 경로 설정
		UUID uuid = UUID.randomUUID();
		String storeFilename = String.join(".", uuid.toString(), extension);
		Path storeAbsolutePath = rootLocation.resolve(storeFilename).normalize().toAbsolutePath();

		try {
			// 로컬 파일 저장
			file.transferTo(storeAbsolutePath);
		} catch (IOException e) {
			throw new StorageException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public Path load(String filename) {
		return rootLocation.resolve(filename);
	}

	@Override
	public Resource loadAsResource(String filename) {
		try {
			Path file = load(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new StorageException(ErrorCode.INTERNAL_SERVER_ERROR);
			}
		} catch (MalformedURLException e) {
			throw new StorageException(ErrorCode.INTERNAL_SERVER_ERROR);
		}
	}

}
