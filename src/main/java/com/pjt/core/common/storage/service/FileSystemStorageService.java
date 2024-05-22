package com.pjt.core.common.storage.service;

import com.pjt.core.common.error.exception.StorageException;
import com.pjt.core.common.error.response.ErrorCode;
import com.pjt.core.common.storage.dto.StorageResponse;
import com.pjt.core.common.storage.entity.Storage;
import com.pjt.core.common.storage.mapper.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
	private final StorageRepository storageRepository;

	@Autowired
	public FileSystemStorageService(StorageProperties properties, StorageRepository storageRepository) {
		if (properties.getLocation().trim().isEmpty()) {
			// 파일 디렉토리 생성 중 에러가 발생하였습니다.
			throw new StorageException(ErrorCode.CREATE_DIRECTORY_ERROR);
		}

		// 파일업로드 경로 설정 초기화
		rootLocation = Paths.get(properties.getLocation()).normalize().toAbsolutePath();
		init();

		this.storageRepository = storageRepository;
	}

	/**
	 * 파일 저장 경로 초기화
	 * @throws StorageException 파일 디렉토리 생성 중 에러가 발생하였습니다.
	 */
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

	/**
	 * 파일 저장
	 * @param file 파일
	 * @return 저장 파일 정보
	 * @throws StorageException 파일 정보가 존재하지 않습니다.
	 * @throws StorageException 파일 저장 중 에러가 발생하였습니다.
	 */
	@Transactional
	@Override
	public StorageResponse store(MultipartFile file) {
		// 빈 파일 체크
		if (file.isEmpty()) {
			// 파일 정보가 존재하지 않습니다.
			throw new StorageException(ErrorCode.FILE_NOT_EXIST);
		}

		// 파일 정보
		String originalFilenameWithExtension = file.getOriginalFilename();
		String extension = originalFilenameWithExtension.substring(originalFilenameWithExtension.lastIndexOf(".") + 1);
		String originalFilename = file.getOriginalFilename().substring(0, originalFilenameWithExtension.lastIndexOf("."));

		// 저장 경로 설정
		String storeFilename = UUID.randomUUID().toString();
		String storeFilenameWithExtension = String.join(".", storeFilename, extension);
		Path storeAbsolutePath = rootLocation.resolve(storeFilenameWithExtension).normalize().toAbsolutePath();

		try {
			// DB 저장
			Storage storage = Storage.builder()
					.imgOriginNm(originalFilename)
					.imgSaveNm(storeFilename)
					.imgExt(extension)
					.imgFileSize(file.getSize())
					.fileUrlPath(storeAbsolutePath.toString())
					.build();

			Storage savedStorage = storageRepository.save(storage);

			// 로컬 파일 저장
			file.transferTo(storeAbsolutePath);

			return StorageResponse.fromEntity(savedStorage);
		} catch (IOException e) {
			// 파일 저장 중 에러가 발생하였습니다.
			throw new StorageException(ErrorCode.SAVE_FILE_ERROR);
		}
	}

	/**
	 * 파일 다중 저장
	 * @param files 파일 리스트
	 * @return 저장 파일 정보 리스트
	 * @throws StorageException 파일 정보가 존재하지 않습니다.
	 * @throws StorageException 파일 저장 중 에러가 발생하였습니다.
	 */
	@Transactional
	@Override
	public List<StorageResponse> storeAll(List<MultipartFile> files) {
		return files.stream().map(this::store).toList();
	}

	/**
	 * 파일 조회
	 * @param filename 파일명
	 * @return 파일 경로
	 */
	@Override
	public Path load(String filename) {
		return rootLocation.resolve(filename);
	}

	/**
	 * 파일 조회
	 * @param filename 파일명
	 * @return 파일
	 * @throws StorageException 유효하지 않는 파일 경로입니다.
	 * @throws StorageException 존재하지 않는 파일이거나 유효한 파일이 아닙니다.
	 */
	@Override
	public Resource loadAsResource(String filename) {
		try {
			// DB 파일 조회
			Storage storage = storageRepository.findByImgSaveNm(filename)
					.orElseThrow(() -> new StorageException(ErrorCode.INVALID_FILE));
			String filenameWithExtension = storage.getFilenameWithExtension();

			// 물리 파일 조회
			Path file = load(filenameWithExtension);
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

	/**
	 * 파일 삭제
	 * @param filename 파일명
	 * @throws StorageException 존재하지 않는 파일이거나 유효한 파일이 아닙니다.
	 */
	@Transactional
	@Override
	public void delete(String filename) {
		try {
			// DB 파일 삭제
			Storage storage = storageRepository.findByImgSaveNm(filename)
					.orElseThrow(() -> new StorageException(ErrorCode.INVALID_FILE));
			storageRepository.deleteByImgSaveNm(storage.getImgSaveNm());

			File file = rootLocation.resolve(Paths.get(storage.getFilenameWithExtension())).toFile();

			// 물리 파일 삭제
			if (file.exists() || file.isFile()) {
				FileSystemUtils.deleteRecursively(file);
			}
		} catch (Exception e) {
			// 존재하지 않는 파일이거나 유효한 파일이 아닙니다.
			throw new StorageException(ErrorCode.INVALID_FILE);
		}
	}

	/**
	 * 파일 다중 삭제
	 * @param filenames 파일명 리스트
	 * @throws StorageException 존재하지 않는 파일이거나 유효한 파일이 아닙니다.
	 */
	@Override
	public void deleteAll(List<String> filenames) {
		filenames.forEach(this::delete);
	}

}
