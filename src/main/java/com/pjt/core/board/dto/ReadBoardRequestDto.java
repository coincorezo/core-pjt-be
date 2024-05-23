package com.pjt.core.board.dto;

import com.pjt.core.common.paging.Paging;
import lombok.Data;

@Data
public class ReadBoardRequestDto extends Paging {

    private String category;
    private String searchCode;
    private String searchKeyword;
}


