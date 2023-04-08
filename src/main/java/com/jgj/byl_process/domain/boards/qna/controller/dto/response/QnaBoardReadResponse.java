package com.jgj.byl_process.domain.boards.qna.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@Getter
@AllArgsConstructor
public class QnaBoardReadResponse {
    private Long qnaBoardId;
    private String title;
    private String writer;
    private String content;
    private Date regDate;

}
