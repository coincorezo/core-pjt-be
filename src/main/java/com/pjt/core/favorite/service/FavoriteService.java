package com.pjt.core.favorite.service;

import com.pjt.core.favorite.dto.CreateFavoriteRequestDto;
import com.pjt.core.favorite.repository.FavoriteMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
/*
 * <pre>
 *  좋아요 Service
 * </pre>
 * @author      : jayeon
 * @date        : 2024-05-24
*/
public class FavoriteService {

    @Autowired
    private FavoriteMapper favoriteMapper;

    /*
     * <pre>
     * 좋아요 등록 전 사용자 기존 등록 여부 확인
     * </pre>
     *
     * @author      : jayeon
     * @date        : 2024-05-24
     * @param       : CreateFavoriteRequestDto
     * @return      : String
     * @throws      :
    */
    public String getFavoriteYn(CreateFavoriteRequestDto createFavoriteRequestDto) {
        return favoriteMapper.getFavoriteYn(createFavoriteRequestDto);
    }

    /*
     * <pre>
     * 좋아요 등록하기
     * </pre>
     *
     * @author      : jayeon
     * @date        : 2024-05-24
     * @param       :
     * @return      :
     * @throws      :
    */
    public void insertFavorite(CreateFavoriteRequestDto createFavoriteRequestDto) {
        favoriteMapper.insertFavorite(createFavoriteRequestDto);
    }

    /* 
     * <pre>
     * 좋아요 삭제하기
     * </pre>
     *
     * @author      : jayeon
     * @date        : 2024-05-24
     * @param       : CreateFavoriteRequestDto
     * @return      : 
     * @throws      : 
    */
    public void deleteFavorite(CreateFavoriteRequestDto createFavoriteRequestDto) {
        favoriteMapper.deleteFavorite(createFavoriteRequestDto);
    }
}
