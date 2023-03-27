package com.jgj.byl_process.domain.boards.qna.service;

import com.jgj.byl_process.domain.boards.qna.controller.request.qnaBoardRequest;
import com.jgj.byl_process.domain.boards.qna.entity.qnaBoard;
import com.jgj.byl_process.domain.boards.qna.repository.qnaBoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class qnaBoardServiceImpl implements qnaBoardService {

    final private qnaBoardRepository qnaBoardRepository;

    public qnaBoardServiceImpl(qnaBoardRepository qnaBoardRepository) {
        this.qnaBoardRepository = qnaBoardRepository;
    }


    @Override
    public void register(qnaBoardRequest qnaBoardRequest) {
        qnaBoard qnaBoard = new qnaBoard();
        qnaBoard.setTitle(qnaBoardRequest.getTitle());
        qnaBoard.setWriter(qnaBoardRequest.getWriter());
        qnaBoard.setContent(qnaBoardRequest.getContent());

        qnaBoardRepository.save(qnaBoard);
    }
    @Override
    public List<qnaBoard> list() {
        return qnaBoardRepository.findAll(Sort.by(Sort.Direction.DESC, "QnaBoardId"));
    }

    @Override
    public qnaBoard read(Long qnaBoardId) {
        Optional<qnaBoard> maybeQnaBoard = qnaBoardRepository.findById(qnaBoardId);

        if(maybeQnaBoard.isEmpty()){
            log.info("찾을 수가 없습니다.");
            return null;
        }
        System.out.println("maybeQuestionBoard read() : " + maybeQnaBoard);

        return maybeQnaBoard.get();
    }

    @Override
    public qnaBoard modify(Long qnaBoardId, qnaBoardRequest qnaBoardRequest) {
        Optional<qnaBoard> maybeQnaBoard = qnaBoardRepository.findById(qnaBoardId);

        if (maybeQnaBoard.isEmpty()) {
            System.out.println("QnaBoard 정보를 찾을 수 없습니다. : " + qnaBoardId);
            return null;
        }

        qnaBoard qnaBoard = maybeQnaBoard.get();
        qnaBoard.setTitle(qnaBoardRequest.getTitle());
        qnaBoard.setContent(qnaBoardRequest.getContent());

        qnaBoardRepository.save(qnaBoard);

        return qnaBoard;
    }

    @Override
    public void remove(Long qnaBoardId) { qnaBoardRepository.deleteById(qnaBoardId);}

    @Override
    public Long getLastEntityId() {
        qnaBoard qnaBoard = qnaBoardRepository.findFirstByOrderByQnaBoardIdDesc();
        return qnaBoard.getQnaBoardId();
    }

}
