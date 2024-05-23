package com.pjt.core.board.dto.boardJ;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateReplyRequestDto {
    @Schema(description="댓글 아이디")
    @NotNull(message = "댓글 ID는 필수입니다.")
    private Integer replyId;

    @Schema(description="게시글 아이디")
    @NotNull(message = "게시글 ID는 필수입니다.")
    private Integer boardId;

    @Schema(description="댓글 내용")
    @NotNull(message = "댓글 내용은 필수입니다.")
    private String replyComment;
}
