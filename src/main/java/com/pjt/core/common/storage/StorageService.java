package com.pjt.core.common.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;

public interface StorageService {

	void init();

	void store(MultipartFile file, StorageImageType storageImageType, String typeKey);

	void storeAll(List<MultipartFile> files, StorageImageType storageImageType, String typeKey);

	Path load(String filename);

	Resource loadAsResource(String filename);

	void delete(String filename);

	void deleteAll(List<String> filenames);

}
