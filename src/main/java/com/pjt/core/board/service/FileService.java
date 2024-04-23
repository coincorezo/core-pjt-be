package com.pjt.core.board.service;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.pjt.core.board.dto.FileRequestDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileService {

	public FileRequestDto uploadFile(List<MultipartFile> files) {
		
		if(!CollectionUtils.isEmpty(files)) {
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
			String date = now.format(formatter);
			
			String absolutePath = new File("").getAbsolutePath() + File.separator + File.separator;
			
			String path = "File" + File.separator + date;
			File multiFile = new File(path);
			
			
			for(MultipartFile file : files) {
				String originalFileName = file.getOriginalFilename();
				String type = file.getContentType();
				
				// 새로운 파일명 생성
				String newFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
				
				// TODO 작업필요함~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			}
		}
		
		
		
		
		
		return null;
	}

}
