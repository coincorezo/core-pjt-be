package com.pjt.core.emoticon.service;

import com.pjt.core.board.dto.FileResponseDto;
import com.pjt.core.board.service.FileService;
import com.pjt.core.emoticon.dto.*;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    @Autowired
    private FileService fileService;


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

        //TODO 대표 이미지를 가지고 오려면 detail을 조회해서 emoticon_detail_id를 찾은다음
        //이미지를 찾아야함
        /*
        for(ReadEmoticonResponseDto item : list) {
            List<EmoticonDetail> detailList = emoticonMapper.getDetailEmoticon(item.getEmoticonId());
        }
        */

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

        // 이미지 조회
        for(EmoticonDetail detail : detailEmoticons) {
            EmoticonImg img = emoticonMapper.getEmoticonImg(detail.getEmoticonDetailId());
            detail.setEmoticonImgNo(img.getEmoticonImgNo());
            detail.setEmoticonDetailId(img.getEmoticonDetailId());
            detail.setEmoticonImgNm(img.getEmoticonImgNm());
            detail.setImgExtNm(img.getImgExtNm());
            detail.setImgFileSize(img.getImgFileSize());
            detail.setFileUrlPath(img.getFileUrlPath());
        }
        
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

    /* 
     * <pre>
     * 이모티콘 좋아요 삭제하기
     * </pre>
     *
     * @author      : jayeon
     * @date        : 2024-05-27
     * @param       : Integer
     * @return      : String
     * @throws      : 
    */
    public String deleteFavorite(Integer emoticonId) {
        // 사용자 정보
        CurrentUser currentUSer = userService.getLoginUser();
        CreateFavoriteRequestDto createFavoriteRequestDto = new CreateFavoriteRequestDto();
        createFavoriteRequestDto.setUserId(currentUSer.getId());
        createFavoriteRequestDto.setEmoticonId(emoticonId);

        
        favoriteService.deleteFavorite(createFavoriteRequestDto);
        
        return "삭제 되었습니다.";
    }

    /* 
     * <pre>
     * 이모티콘 등록
     * </pre>
     *
     * @author      : jayeon
     * @date        : 2024-05-27
     * @param       : 
     * @return      : 
     * @throws      : 
    */
    public String createEmoticon(CreateEmoticonRequestDto dto, List<CreateEmoticonDetailRequestDto> detailList, MultipartFile[] files) throws IOException {
        CurrentUser currentUser = userService.getLoginUser();
        dto.setUserId(currentUser.getId());

        // [1]. 이모티콘 등록
        int insertResult = emoticonMapper.createEmoticon(dto);

        if(insertResult > 0) {
            int index = 1;

            // [2]. 이모티콘 상세 등록
            for(CreateEmoticonDetailRequestDto detail : detailList) {
                String emoticonDetailId = dto.getEmoticonId() + "_" + index;

                detail.setEmoticonId(dto.getEmoticonId());
                detail.setEmoticonDetailId(emoticonDetailId);

                // 상세 이모티콘 정보 등록
                emoticonMapper.createEmoticonDetail(detail);
                // 상세 이모티콘 이미지 저장 (TODO: 이렇게 index - 1 값으로 넣었을 때 혹시나 IndexOutOfBoudsException 없을지 생각해보기)
                insertEmoticonImage(detail, files[index - 1]);
                index++;
            }
        }

        return "이모티콘이 등록 됐습니다.";
    }

    /*
     * <pre>
     * 이모티콘 파일 등록(파일 처리)
     * </pre>
     *
     * @author      : jayeon
     * @date        : 2024-05-27
     * @param       :
     * @return      :
     * @throws      :
    */
    public void insertEmoticonImage(CreateEmoticonDetailRequestDto detail, MultipartFile file) throws IOException {
        // [1]. 파일 물리적 저장 처리
        FileResponseDto savedFile = fileService.uploadSingleImage(file);
        savedFile.setEmoticonDetailId(detail.getEmoticonDetailId());

        // [2]. 파일 논리적 저장 처리
        emoticonMapper.insertImoticon(savedFile);
    }
}
