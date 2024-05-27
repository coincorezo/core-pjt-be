package com.pjt.core.emoticon.controller;

import com.pjt.core.emoticon.dto.ReadEmoticonDetailResponseDto;
import com.pjt.core.emoticon.dto.ReadEmoticonResponseDto;

import com.pjt.core.emoticon.service.EmoticonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emoticon")
@RequiredArgsConstructor
/*
 * <pre>
 *  이모티콘 controller
 * </pre>
 * @author      : jayeon
 * @date        : 2024-05-24
*/
public class EmoticonController {

    @Autowired
    EmoticonService emoticonService;
    
    //TODO 페이징

    /*
     * <pre>
     * 이모티콘 목록 조회
     * </pre>
     *
     * @author      : jayeon
     * @date        : 2024-05-24
     * @param       : String
     * @return      : HttpEntity
     * @throws      :
    */
    @GetMapping
    public HttpEntity<List<ReadEmoticonResponseDto>> getEmoticonList(@RequestParam("searchKeyword") String searchCode) {
        List<ReadEmoticonResponseDto> list =  emoticonService.getEmoticonList(searchCode);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /* 
     * <pre>
     * 이모티콘 상세 조회
     * </pre>
     *
     * @author      : jayeon
     * @date        : 2024-05-24
     * @param       : Integer
     * @return      : HttpEntity
     * @throws      : 
    */
    @GetMapping("/{emoticonId}")
    public HttpEntity<ReadEmoticonDetailResponseDto> getEmoticonDetail(@PathVariable Integer emoticonId) {
        ReadEmoticonDetailResponseDto dto = emoticonService.getEmoticonDetail(emoticonId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    /* 
     * <pre>
     * 이모티콘 좋아요 등록하기
     * </pre>
     *
     * @author      : jayeon
     * @date        : 2024-05-24
     * @param       : Integer
     * @return      : HttpEntity
     * @throws      : 
    */
    @PostMapping("/favorite/{emoticonId}")
    public HttpEntity<String> createFavorite(@PathVariable("emoticonId") Integer emoticonId) {
        String result = emoticonService.createFavorite(emoticonId);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    /* 
     * <pre>
     * 이모티콘 좋아요 삭제하기
     * </pre>
     *
     * @author      : jayeon
     * @date        : 2024-05-24
     * @param       : Integer
     * @return      : HttpEntity
     * @throws      : 
    */
    @DeleteMapping("/favorite/{emoticonId}")
    public HttpEntity<String> deleteFavorite(@PathVariable("emoticonId") Integer emoticonId) {
        String result = emoticonService.deleteFavorite(emoticonId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }



}
