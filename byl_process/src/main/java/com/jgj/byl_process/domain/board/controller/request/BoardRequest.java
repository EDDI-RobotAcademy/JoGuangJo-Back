package com.jgj.byl_process.domain.board.controller.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardRequest {

    private String title;
    private String writer;
    private String content;
}
