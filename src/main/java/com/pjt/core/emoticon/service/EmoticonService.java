package com.pjt.core.emoticon.service;

import com.pjt.core.emoticon.dto.ReadEmoticonDetailResponseDto;
import com.pjt.core.emoticon.dto.ReadEmoticonRequestDto;
import com.pjt.core.emoticon.dto.ReadEmoticonResponseDto;
import com.pjt.core.emoticon.dto.EmoticonDetail;
import com.pjt.core.emoticon.entity.EmoticonImg;
import com.pjt.core.emoticon.repository.EmoticonMapper;
import com.pjt.core.favorite.dto.CreateFavoriteRequestDto;
import com.pjt.core.favorite.service.FavoriteService;
import com.pjt.core.user.dto.CurrentUser;
import com.pjt.core.user.mapper.UserMapper;
import com.pjt.core.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
/*
 * <pre>
 * 이모티콘 Service
 * </pre>
 * @author      : jayeon
 * @date        : 2024-05-24
*/
public class EmoticonService {

    @Autowired
    EmoticonMapper emoticonMapper;

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private FavoriteService favoriteService;


    /*
     * <pre>
     * 이모티콘 목록 조회
     * </pre>
     *
     * @author      : jayeon
     * @date        : 2024-05-24
     * @param       : ReadEmoticonRequestDto
     * @return      : List<ReadEmoticonResponseDto>
     * @throws      :
    */
    public List<ReadEmoticonResponseDto> getEmoticonList(String searchCode) {
        ReadEmoticonRequestDto dto = new ReadEmoticonRequestDto();
        CurrentUser currentUser = userService.getLoginUser();

        dto.setUserId(currentUser.getId());
        dto.setSearchCode(searchCode);

        List<ReadEmoticonResponseDto> list = emoticonMapper.getEmoticonList(dto);
        return list;
    }

    /* 
     * <pre>
     * 이모티콘 상세 조회
     * </pre>
     *
     * @author      : jayeon
     * @date        : 2024-05-24
     * @param       : Integer
     * @return      : ReadEmoticonDetailResponseDto
     * @throws      : 
    */
    public ReadEmoticonDetailResponseDto getEmoticonDetail(Integer emoticonId) {
        List<EmoticonImg> emoticonImgList = new ArrayList<>();

        // 사용자 정보
        CurrentUser currentUser = userService.getLoginUser();

        // 이모티콘 상세 정보 조회
        ReadEmoticonRequestDto readEmoticonRequestDto = new ReadEmoticonRequestDto();
        readEmoticonRequestDto.setEmoticonId(emoticonId);
        readEmoticonRequestDto.setUserId(currentUser.getId());

        ReadEmoticonDetailResponseDto dto = emoticonMapper.getEmoticonInfo(readEmoticonRequestDto);

        // 상세 이모티콘들 조회
        List<EmoticonDetail> detailEmoticons = emoticonMapper.getDetailEmoticon(emoticonId);
        dto.setEmoticonDetailList(detailEmoticons);

        return dto;
    }


    /*
     * <pre>
     * 이모티콘 좋아요 등록하기
     * </pre>
     *
     * @author      : jayeon
     * @date        : 2024-05-24
     * @param       : Integer
     * @return      : String
     * @throws      :
     */
    public String createFavorite(Integer emoticonId) {
        // 사용자 정보
        CurrentUser currentUSer = userService.getLoginUser();
        CreateFavoriteRequestDto createFavoriteRequestDto = new CreateFavoriteRequestDto();
        createFavoriteRequestDto.setUserId(currentUSer.getId());
        createFavoriteRequestDto.setEmoticonId(emoticonId);

        // 기존에 좋아요 등록 되어있는지 확인 (bug)
        String checkedId = favoriteService.getFavoriteYn(createFavoriteRequestDto);

        if(StringUtils.isNotBlank(checkedId)) {
            // 좋아요 삭제
            favoriteService.deleteFavorite(createFavoriteRequestDto);
        }

        // 좋아요 등록
        favoriteService.insertFavorite(createFavoriteRequestDto);

        return "등록 되었습니다.";
    }

    public String deleteFavorite(Integer emoticonId) {
        // 사용자 정보
        CurrentUser currentUSer = userService.getLoginUser();
        CreateFavoriteRequestDto createFavoriteRequestDto = new CreateFavoriteRequestDto();
        createFavoriteRequestDto.setUserId(currentUSer.getId());
        createFavoriteRequestDto.setEmoticonId(emoticonId);

        
        favoriteService.deleteFavorite(createFavoriteRequestDto);
        
        return "삭제 되었습니다.";
    }
}
