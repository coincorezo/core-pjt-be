package com.pjt.core.example.repository;

import com.pjt.core.example.entity.Book;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookMapper {

	Book selectBookById(Long id);

}
