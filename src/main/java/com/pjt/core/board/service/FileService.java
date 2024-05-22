package com.pjt.core.board.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.pjt.core.board.dto.FileRequestDto;
import com.pjt.core.board.dto.FileResponseDto;
import com.pjt.core.board.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileService {

	// private final String rootPath = System.getProperty("user.dir") + "/files/";
	private final String rootPath = System.getProperty("user.dir");

//	@Value("${file.dir}")
//    private String rootPath;
	
	private final BoardMapper boardMapper;
	
	public List<FileResponseDto> uploadFile(Integer boardId, List<MultipartFile> files) throws IllegalStateException, IOException {
		
		List<FileResponseDto> savedFiles = new ArrayList<FileResponseDto>();
		
		if(!CollectionUtils.isEmpty(files)) {
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
			String date = now.format(formatter);
			
			String absolutePath = new File("").getAbsolutePath() + File.separator + File.separator;
			
			// 프로젝트 내부에 폴더 생성 (files -> 현재날짜)
			// String path = rootPath +  File.separator + date;
			String path = rootPath + File.separator + "src/main/resources/upload/files" + date;

			File saveFile = new File(path);
			
			// 디렉토리 만들기
			if(!saveFile.exists()) {
				saveFile.mkdirs();
			}

			for(MultipartFile file : files) {
				int index = file.getOriginalFilename().indexOf(".");
				
				// 원본 파일명
				String originalFileName = file.getOriginalFilename().substring(0,index);
				// 파일 type
				String type = file.getContentType();
				
				// 새로운 파일명 생성 (UUID_원본파일명)
				String newFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
				
			
				// saveFile = new File(path + File.separator + newFileName);
				saveFile = new File(path + newFileName);
				file.transferTo(saveFile);
				
				FileResponseDto save = new FileResponseDto();
				save.setUploadFilePath(saveFile.getAbsolutePath());
				save.setSavedName(newFileName);
				save.setImgFileSize(file.getSize());
				save.setEmoticonImgNm(originalFileName); 
				save.setImgExtNm(file.getContentType());
				save.setBoardId(boardId);

				savedFiles.add(save);
			
				
			}
		}
		
		
		
		
		
		return savedFiles;
	}

	public void deleteImg(List<String> deleteImgNo) {
		// 저장 경로 찾기
		for(String imgNo : deleteImgNo) {
			String fileUrlPath = boardMapper.getFileUrlPath(imgNo);
			// 물리적 삭제
			File file = new File(fileUrlPath);
			boolean isDeleted = file.delete();
			if(!isDeleted) {
				isDeleted = file.delete();
			}
		}

	}

}
