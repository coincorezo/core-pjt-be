package com.pjt.core.common.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import jakarta.annotation.Resource;

//파일 업로드
public class FileUploadUtile {
	@Resource(name = "uploadPath")
	String uploadPath;

	public Map<String, Object> fileUpload(MultipartHttpServletRequest multiRequest)
			throws IllegalStateException, IOException {
//		String filePath = "C:\\core\\corebeack\\src\\main\\resources\\dowload";
		String filePath = "C:\\core-pjt\\imgDowload";

		Map<String, Object> fileInfo = new HashMap<String, Object>();
		// 파라미터 이름을 키로 파일 정보를 값으로 하는 MAP을 가져온다
		Map<String, MultipartFile> files = multiRequest.getFileMap();

		// files.entrySet()의 요소를 가져온다.
		Iterator<Map.Entry<String, MultipartFile>> itr = files.entrySet().iterator();

		// MultipartFile 초기화
		// MultipartFile file =

		MultipartFile file = multiRequest.getFile("file");
		System.out.println(file.getOriginalFilename());
		String saveName = file.getOriginalFilename();
		// 물리적 파일 이름
		saveName = this.upLoadUnitqueFileNme(saveName, file.getBytes());
		String fileName = file.getOriginalFilename();
		String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
		fileInfo.put("fileName", file.getOriginalFilename()); // filename
		fileInfo.put("extension", extension); // 확장자
		fileInfo.put("emoticonPhysicalNm", saveName); // 물리적 name
		fileInfo.put("imgFileSize", file.getSize()); // 이미지 사이즈
		fileInfo.put("filePath", filePath);

		// 읽어 올 요소가 있으면 true , 없으면 false 를 반환
		while (itr.hasNext()) {
			Map.Entry<String, MultipartFile> entry = itr.next();
			// entry 에 값을 가져온다
			file = entry.getValue();
			// 원본 파일명, 저장 될 파일명 생성
			String fileOrgName = file.getOriginalFilename();

			if (!fileOrgName.isEmpty()) {
				// filePath 에 해당되는 파일 File 객체를 생성
				File fileFolder = new File(filePath);

				if (!fileFolder.exists()) {
					// 부모 폴더까지 포함하여 경로에 폴더 생성
					if (fileFolder.mkdir()) {
						System.out.println("[file.mkdirs].Sucess");
					}
				}
				File saveFile = new File(filePath, saveName);
				// File saveFile = new File(filePath, fileOrgName);
				// 생성한 파일 객체를 업로드 처리하지 않으면 임시파일에 저장된 파일이 자동적으로 삭제되기 때문에
				// transferTo(File f) 메서드를 이용해서 업로드 처리
				file.transferTo(saveFile);
			}
		}

		return fileInfo;
	}

	public String upLoadUnitqueFileNme(String originalName, byte[] fileData) throws IOException {

		// 파일명 랜덤 생성 메서드
		UUID uuid = UUID.randomUUID(); // 범용고유식별자
		String saveName = uuid.toString() + "_" + originalName;

		File target = new File(uploadPath, saveName);
		// 임시디렉토리에 저장된 업로드된 파일을 지정된 디렉토리로 복사
		FileCopyUtils.copy(fileData, target);

		return saveName;
	}

}
