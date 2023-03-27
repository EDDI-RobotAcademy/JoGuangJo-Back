package com.jgj.byl_process.domain.boards.qna.service;

import com.jgj.byl_process.domain.boards.qna.controller.request.qnaBoardRequest;
import com.jgj.byl_process.domain.boards.qna.entity.qnaBoard;

import java.util.List;

public interface qnaBoardService {
    public void register(qnaBoardRequest qnaBoardRequest);

    List<qnaBoard> list();

    qnaBoard read(Long qnaBoardId);

    qnaBoard modify(Long qnaBoardId, qnaBoardRequest qnaBoardRequest);

    void remove(Long qnaBoardId);

    Long getLastEntityId();
}
