package com.jgj.byl_process.domain.board.controller.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NoticeBoardRequest {

    private String title;
    private String writer;
    private String content;
}
