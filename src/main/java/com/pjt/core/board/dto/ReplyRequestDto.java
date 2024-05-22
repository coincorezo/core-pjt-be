package com.pjt.core.board.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReplyRequestDto {

    @NotNull(message = "게시글 ID는 필수입니다.")
    private Integer boardId;
    @NotBlank(message = "댓글 내용은 필수입니다.")
    private String replyComment;

}
