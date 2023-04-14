package com.jgj.byl_process.domain.boards.qna.controller.dto.response;

import com.jgj.byl_process.domain.boards.qna.entity.QnaBoardImgResource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class QnaBoardModifyResponse {

    private Long qnaBoardId;
    private String title;
    private String writer;
    private String content;
    private String imageResourcePath;
    private Date regDate;


}
