package com.pjt.core.common.storage;

import com.pjt.core.common.error.exception.StorageException;
import com.pjt.core.common.error.response.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class FileSystemStorageService implements StorageService {

	private final Path rootLocation;

	@Autowired
	public FileSystemStorageService(StorageProperties properties) {
		if (properties.getLocation().trim().isEmpty()) {
			// 파일 디렉토리 생성 중 에러가 발생하였습니다.
			throw new StorageException(ErrorCode.CREATE_DIRECTORY_ERROR);
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
			// 파일 디렉토리 생성 중 에러가 발생하였습니다.
			throw new StorageException(ErrorCode.CREATE_DIRECTORY_ERROR);
		}
	}

	@Override
	public void store(MultipartFile file) {
		// 빈 파일 체크
		if (file.isEmpty()) {
			// 파일 정보가 존재하지 않습니다.
			throw new StorageException(ErrorCode.FILE_NOT_EXIST);
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
			// 파일 저장 중 에러가 발생하였습니다.
			throw new StorageException(ErrorCode.SAVE_FILE_ERROR);
		}
	}

	@Override
	public void storeAll(List<MultipartFile> files) {
		files.forEach(this::store);
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
				// 존재하지 않는 파일이거나 유효한 파일이 아닙니다.
				throw new StorageException(ErrorCode.INVALID_FILE);
			}
		} catch (MalformedURLException e) {
			// 유효하지 않는 파일 경로입니다.
			throw new StorageException(ErrorCode.INVALID_DIRECTORY);
		}
	}

	@Override
	public void delete(String filename) {
		File file = rootLocation.resolve(Paths.get(filename)).toFile();

		if (file.exists() || file.isFile()) {
			FileSystemUtils.deleteRecursively(file);
		} else {
			// 존재하지 않는 파일이거나 유효한 파일이 아닙니다.
			throw new StorageException(ErrorCode.INVALID_FILE);
		}
	}

	@Override
	public void deleteAll(List<String> filenames) {
		filenames.forEach(this::delete);
	}

}
