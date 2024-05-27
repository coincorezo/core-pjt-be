package com.pjt.core.emoticon.repository;

import com.pjt.core.board.dto.FileResponseDto;
import com.pjt.core.emoticon.dto.*;
import com.pjt.core.emoticon.entity.EmoticonImg;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmoticonMapper {

    // 이모티콘 리스트 조회
    List<ReadEmoticonResponseDto> getEmoticonList(ReadEmoticonRequestDto dto);

    // 이모티콘 상세 정보 조회
    ReadEmoticonDetailResponseDto getEmoticonInfo(ReadEmoticonRequestDto readEmoticonRequestDto);

    // 이모티콘 상세 리스트 조회
    List<EmoticonDetail> getDetailEmoticon(Integer emoticonId);
    
    // 이모티콘 이미지 정보 조회
    EmoticonImg getEmoticonImg(String emoticonDetailId);

    // 이모티콘 등록
    int createEmoticon(CreateEmoticonRequestDto dto);

    // 이모티콘 상세 등록
    void createEmoticonDetail(CreateEmoticonDetailRequestDto detail);

    // 이모티콘 파일 등록(논리)
    void insertImoticon(FileResponseDto savedFile);
}
