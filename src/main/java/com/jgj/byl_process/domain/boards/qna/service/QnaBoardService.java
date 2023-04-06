package com.jgj.byl_process.domain.boards.qna.service;

import com.jgj.byl_process.domain.boards.qna.controller.dto.request.QnaBoardRequest;
import com.jgj.byl_process.domain.boards.qna.entity.QnaBoard;

import java.util.List;

public interface QnaBoardService {
    void register(QnaBoardRequest qnaBoardRequest);

    List<QnaBoard> list();

    QnaBoard read(Long qnaBoardId);

    QnaBoard modify(Long qnaBoardId, QnaBoardRequest qnaBoardRequest);

    void remove(Long qnaBoardId);

    Long getLastEntityId();
}
