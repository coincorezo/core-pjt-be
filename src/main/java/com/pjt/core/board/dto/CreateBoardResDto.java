package com.pjt.core.board.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CreateBoardResDto {

    @Schema(description = "게시글 ID")
    private int boardId;

    @Schema(description = "코인 값")
    private int coin;

}
