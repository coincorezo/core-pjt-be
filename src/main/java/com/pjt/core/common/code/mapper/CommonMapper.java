package com.pjt.core.common.code.mapper;

import com.pjt.core.common.code.dto.ReadCommonCodeResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommonMapper {

	List<ReadCommonCodeResponse> selectCommonCode(String commonCode);

}
