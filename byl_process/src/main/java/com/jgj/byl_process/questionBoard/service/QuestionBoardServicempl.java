package com.jgj.byl_process.questionBoard.service;

import com.jgj.byl_process.questionBoard.controller.request.QuestionBoardRequest;
import com.jgj.byl_process.questionBoard.entity.QuestionBoard;
import com.jgj.byl_process.questionBoard.repository.QuestionBoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class QuestionBoardServicempl implements QuestionBoardService {

    final private QuestionBoardRepository questionBoardRepository;

    public QuestionBoardServicempl(QuestionBoardRepository questionBoardRepository) {
        this.questionBoardRepository = questionBoardRepository;
    }


    @Override
    public void register(QuestionBoardRequest questionBoardRequest) {
        QuestionBoard questionBoard = new QuestionBoard();
        questionBoard.setTitle(questionBoardRequest.getTitle());
        questionBoard.setWriter(questionBoardRequest.getWriter());
        questionBoard.setContent(questionBoardRequest.getContent());

        questionBoardRepository.save(questionBoard);
    }

}
