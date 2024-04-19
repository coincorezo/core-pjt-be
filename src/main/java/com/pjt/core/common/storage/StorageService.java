package com.pjt.core.common.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;

public interface StorageService {

	void init();

	void store(MultipartFile file);

	void storeAll(List<MultipartFile> files);

	Path load(String filename);

	Resource loadAsResource(String filename);

	void delete(String filename);

	void deleteAll(List<String> filenames);

}
