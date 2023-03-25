package com.jgj.byl_process.questionBoard.service;

import com.jgj.byl_process.questionBoard.controller.request.QuestionBoardRequest;
import com.jgj.byl_process.questionBoard.entity.QuestionBoard;

import java.util.List;

public interface QuestionBoardService {
    public void register(QuestionBoardRequest questionBoardRequest);

    List<QuestionBoard> list();

    QuestionBoard read(Long questionBoardId);

    QuestionBoard modify(Long questionBoardId, QuestionBoardRequest questionBoardRequest);

}
