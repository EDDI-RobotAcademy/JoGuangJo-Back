package com.jgj.byl_process.questionBoard.service;

import com.jgj.byl_process.questionBoard.controller.request.QuestionBoardRequest;
import com.jgj.byl_process.questionBoard.entity.QuestionBoard;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

public interface QuestionBoardService {
    public void register(QuestionBoardRequest questionBoardRequest);

    List<QuestionBoard> list();
}
