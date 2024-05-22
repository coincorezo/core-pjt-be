package com.pjt.core.login;

import org.apache.ibatis.annotations.Mapper;

import com.pjt.core.login.dto.LoginHistory;
import com.pjt.core.login.dto.LoginReqDto;
import com.pjt.core.login.dto.UserResDto;

@Mapper
public interface LoginMapper {

	UserResDto login(LoginReqDto loginReqDto);

	int insertLoginLog(UserResDto user);

	int insertLoginLog(LoginHistory history);
}
