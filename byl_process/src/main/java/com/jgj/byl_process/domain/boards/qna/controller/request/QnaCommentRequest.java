package com.jgj.byl_process.domain.boards.qna.controller.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class QnaCommentRequest {

    final private String comment;
    final private Long qnaBoardId;
}
