package com.pjt.core.common.code.service;

import com.pjt.core.common.code.dto.CreateCommonCodeRequest;
import com.pjt.core.common.code.dto.DeleteCommonCodeRequest;
import com.pjt.core.common.code.dto.ReadCommonCodeResponse;
import com.pjt.core.common.code.dto.UpdateCommonCodeRequest;
import com.pjt.core.common.code.mapper.CommonMapper;
import com.pjt.core.common.error.exception.NoDataException;
import com.pjt.core.common.error.response.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommonService {

	private final CommonMapper commonMapper;

	public List<ReadCommonCodeResponse> getCommonCode(String commonCode) {
		return commonMapper.selectCommonCode(commonCode);
	}

	public void createCommonCode(CreateCommonCodeRequest request) {
		commonMapper.insertCommonCode(request);
	}

	public void updateCommonCode(UpdateCommonCodeRequest request) {
		// 공통코드 존재 여부 확인
		checkExistCommonCode(request.getRef(), request.getCommonCode());

		commonMapper.updateCommonCode(request);
	}

	public void deleteCommonCode(DeleteCommonCodeRequest request) {
		// 공통코드 존재 여부 확인
		checkExistCommonCode(request.getRef(), request.getCommonCode());

		commonMapper.deleteCommonCode(request);
	}

	/**
	 * 공통코드 존재 여부 확인
	 * @param ref 참조 공통코드
	 * @param commonCode 공통코드
	 */
	private void checkExistCommonCode(String ref, String commonCode) {
		// 공통코드 조회
		List<ReadCommonCodeResponse> readCommonCodeResponseList = this.getCommonCode(ref);
		// 공통코드 존재 여부 확인
		boolean matchedCommonCode = readCommonCodeResponseList.stream()
				.anyMatch(response -> response.getCommonCode().equals(commonCode));

		if (readCommonCodeResponseList.isEmpty() || !matchedCommonCode) {
			throw new NoDataException(ErrorCode.NO_DATA);
		}
	}

}
