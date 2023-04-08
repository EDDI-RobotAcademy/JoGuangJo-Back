package com.jgj.byl_process.domain.boards.qna.controller.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Getter
@RequiredArgsConstructor
public class QnaBoardListResponse {

    final private Long qnaBoardId;
    final private String title;
    final private String writer;
    final private Date regDate;

}
