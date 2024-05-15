package com.pjt.core.user.mapper;

import com.pjt.core.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {

	Optional<User> selectUserById(String id);

	int insertUser(User user);

}
