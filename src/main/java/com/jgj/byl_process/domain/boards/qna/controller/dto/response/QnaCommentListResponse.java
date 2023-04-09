package com.jgj.byl_process.domain.boards.qna.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class QnaCommentListResponse {
    private Long qnaCommentId;
    private Long qnaBoardId;
    private String comment;
    private String writer;
}
