package com.jgj.byl_process.questionBoard.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public class QuestionBoardRequest {

   final private String title;
    final private String writer;
    final private String content;

}
