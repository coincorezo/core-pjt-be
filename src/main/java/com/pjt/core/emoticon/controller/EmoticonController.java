package com.pjt.core.emoticon.controller;

import com.pjt.core.emoticon.dto.ReadEmoticonDetailResponseDto;
import com.pjt.core.emoticon.dto.ReadEmoticonResponseDto;

import com.pjt.core.emoticon.service.EmoticonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    /*
     * <pre>
     * 이모티콘 목록 조회
     * </pre>
     *
     * @author      : jayeon
     * @date        : 2024-05-24
     * @param       : String searchCode
     * @return      : List<ReadEmoticonResponseDto>
     * @throws      :
    */
    @GetMapping
    public List<ReadEmoticonResponseDto> getEmoticonList(@RequestParam("searchKeyword") String searchCode) {
        return emoticonService.getEmoticonList(searchCode);
    }

    /* 
     * <pre>
     * 이모티콘 상세 조회
     * </pre>
     *
     * @author      : jayeon
     * @date        : 2024-05-24
     * @param       : Integer emoticonId
     * @return      : ReadEmoticonDetailResponseDto
     * @throws      : 
    */
    @GetMapping("/{emoticonId}")
    public ReadEmoticonDetailResponseDto getEmoticonDetail(@PathVariable Integer emoticonId) {
        return emoticonService.getEmoticonDetail(emoticonId);
    }

}
