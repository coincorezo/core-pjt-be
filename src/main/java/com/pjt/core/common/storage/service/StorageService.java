package com.pjt.core.common.storage.service;

import com.pjt.core.common.storage.entity.Storage;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;

public interface StorageService {

	void init();

	Storage store(MultipartFile file);

	List<Storage> storeAll(List<MultipartFile> files);

	Path load(String filename);

	Resource loadAsResource(String filename);

	void delete(String filename);

	void deleteAll(List<String> filenames);

}
