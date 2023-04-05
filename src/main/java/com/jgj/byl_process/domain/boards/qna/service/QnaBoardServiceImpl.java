package com.jgj.byl_process.domain.boards.qna.service;

import com.jgj.byl_process.domain.boards.qna.controller.request.QnaBoardRequest;
import com.jgj.byl_process.domain.boards.qna.entity.QnaBoard;
import com.jgj.byl_process.domain.boards.qna.repository.QnaBoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class QnaBoardServiceImpl implements QnaBoardService {

    final private QnaBoardRepository qnaBoardRepository;

    public QnaBoardServiceImpl(QnaBoardRepository qnaBoardRepository) {
        this.qnaBoardRepository = qnaBoardRepository;
    }


    @Override
    public void register(QnaBoardRequest qnaBoardRequest) {
        QnaBoard qnaBoard = new QnaBoard();
        qnaBoard.setTitle(qnaBoardRequest.getTitle());
        qnaBoard.setWriter(qnaBoardRequest.getWriter());
        qnaBoard.setContent(qnaBoardRequest.getContent());

        qnaBoardRepository.save(qnaBoard);
    }
    @Override
    public List<QnaBoard> list() {
        return qnaBoardRepository.findAll(Sort.by(Sort.Direction.DESC, "QnaBoardId"));
    }

    @Override
    public QnaBoard read(Long qnaBoardId) {
        Optional<QnaBoard> maybeQnaBoard = qnaBoardRepository.findById(qnaBoardId);

        if(maybeQnaBoard.isEmpty()){
            log.info("찾을 수가 없습니다.");
            return null;
        }
        System.out.println("maybeQuestionBoard read() : " + maybeQnaBoard);

        return maybeQnaBoard.get();
    }

    @Override
    public QnaBoard modify(Long qnaBoardId, QnaBoardRequest qnaBoardRequest) {
        Optional<QnaBoard> maybeQnaBoard = qnaBoardRepository.findById(qnaBoardId);

        if (maybeQnaBoard.isEmpty()) {
            System.out.println("QnaBoard 정보를 찾을 수 없습니다. : " + qnaBoardId);
            return null;
        }

        QnaBoard qnaBoard = maybeQnaBoard.get();
        qnaBoard.setTitle(qnaBoardRequest.getTitle());
        qnaBoard.setContent(qnaBoardRequest.getContent());

        qnaBoardRepository.save(qnaBoard);

        return qnaBoard;
    }

    @Override
    public void remove(Long qnaBoardId) { qnaBoardRepository.deleteById(qnaBoardId);}

    @Override
    public Long getLastEntityId() {
        QnaBoard qnaBoard = qnaBoardRepository.findFirstByOrderByQnaBoardIdDesc();
        return qnaBoard.getQnaBoardId();
    }

}
