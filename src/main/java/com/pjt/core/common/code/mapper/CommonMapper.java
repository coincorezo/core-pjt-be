package com.pjt.core.common.code.mapper;

import com.pjt.core.common.code.dto.CreateCommonCodeRequest;
import com.pjt.core.common.code.dto.DeleteCommonCodeRequest;
import com.pjt.core.common.code.dto.ReadCommonCodeResponse;
import com.pjt.core.common.code.dto.UpdateCommonCodeRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommonMapper {

	List<ReadCommonCodeResponse> selectCommonCode(String commonCode);

	void insertCommonCode(CreateCommonCodeRequest request);

	void updateCommonCode(UpdateCommonCodeRequest request);

	void deleteCommonCode(DeleteCommonCodeRequest request);

}
