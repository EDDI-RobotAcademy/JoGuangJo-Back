package com.jgj.byl_process.questionBoard.service;

import com.jgj.byl_process.questionBoard.controller.request.QuestionBoardRequest;
import com.jgj.byl_process.questionBoard.entity.QuestionBoard;
import com.jgj.byl_process.questionBoard.repository.QuestionBoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class QuestionBoardServiceImpl implements QuestionBoardService {

    final private QuestionBoardRepository questionBoardRepository;

    public QuestionBoardServiceImpl(QuestionBoardRepository questionBoardRepository) {
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
    @Override
    public List<QuestionBoard> list() {
        return questionBoardRepository.findAll(Sort.by(Sort.Direction.DESC, "QuestionBoardId"));
    }

    @Override
    public QuestionBoard read(Long questionBoardId) {
        Optional<QuestionBoard> maybeQuestionBoard = questionBoardRepository.findById(questionBoardId);

        if(maybeQuestionBoard.isEmpty()){
            log.info("찾을 수가 없습니다.");
            return null;
        }
        System.out.println("maybeQuestionBoard read() : " + maybeQuestionBoard);

        return maybeQuestionBoard.get();
    }
}
