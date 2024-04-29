package com.pjt.core.common.code.service;

import com.pjt.core.common.code.dto.ReadCommonCodeResponse;
import com.pjt.core.common.code.mapper.CommonMapper;
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

}
