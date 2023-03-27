package com.jgj.byl_process.domain.boards.qna.controller.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class qnaBoardRequest {

   final private String title;
    final private String writer;
    final private String content;

}
