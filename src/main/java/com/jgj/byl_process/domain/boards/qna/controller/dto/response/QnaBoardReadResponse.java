package com.jgj.byl_process.domain.boards.qna.controller.dto.response;

import com.jgj.byl_process.domain.boards.qna.entity.QnaBoardImgResource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@AllArgsConstructor
@ToString
public class QnaBoardReadResponse {
    private Long qnaBoardId;
    private String title;
    private String writer;
    private String content;
    private String imageResourcePath;
    private Date regDate;

    public QnaBoardReadResponse(Long qnaBoardId, String title, String writer, String content, QnaBoardImgResource qnaBoardImgResource, Date regDate) {
        this.qnaBoardId = qnaBoardId;
        this.title = title;
        this.writer = writer;
        this.content = content;
        if(qnaBoardImgResource != null) {
            this.imageResourcePath = qnaBoardImgResource.getImageResourcePath();
        }
        this.regDate = regDate;
    }
}
