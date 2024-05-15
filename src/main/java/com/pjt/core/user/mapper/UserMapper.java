package com.pjt.core.user.mapper;

import com.pjt.core.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {

	/**
	 * 사용자 조회
	 * @param id 사용자 아이디
	 * @return 사용자 정보
	 */
	Optional<User> selectUserById(String id);

	/**
	 * 사용자 등록
	 * @param user 사용자 정보
	 * @return 성공여부
	 */
	int insertUser(User user);

}
