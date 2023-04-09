package com.jgj.byl_process.domain.boards.qna.service;

import com.jgj.byl_process.domain.boards.qna.controller.dto.request.QnaCommentRequest;
import com.jgj.byl_process.domain.boards.qna.controller.dto.response.QnaCommentListResponse;
import com.jgj.byl_process.domain.boards.qna.entity.QnaComment;

import java.util.List;

public interface QnaCommentService {
    List<QnaCommentListResponse> qnaCommentList(Long qnaBoardId);

    void qnaCommentRegister(QnaCommentRequest qnaCommentRequest);

    QnaComment modify(Long qnaCommentId, QnaCommentRequest qnaCommentRequest);

    void remove(Long qnaCommentId);

}
