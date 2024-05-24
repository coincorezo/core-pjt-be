package com.pjt.core.favorite.repository;

import com.pjt.core.favorite.dto.CreateFavoriteRequestDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FavoriteMapper {
    //  사용자 좋아요 등록 여부 확인
    String getFavoriteYn(CreateFavoriteRequestDto createFavoriteRequestDto);

    // 좋아요 삭제
    void deleteFavorite(CreateFavoriteRequestDto createFavoriteRequestDto);

    // 좋아요 등록
    void insertFavorite(CreateFavoriteRequestDto createFavoriteRequestDto);
}
