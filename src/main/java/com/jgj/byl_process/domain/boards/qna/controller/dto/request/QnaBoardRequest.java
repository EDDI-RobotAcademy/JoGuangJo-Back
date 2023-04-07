package com.jgj.byl_process.domain.boards.qna.controller.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class QnaBoardRequest {

   final private String title;
    final private String writer;
    final private String content;

}
