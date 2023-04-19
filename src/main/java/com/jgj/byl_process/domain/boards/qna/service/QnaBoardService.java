package com.jgj.byl_process.domain.boards.qna.service;

import com.jgj.byl_process.domain.boards.qna.controller.dto.request.QnaBoardRequest;
import com.jgj.byl_process.domain.boards.qna.controller.dto.response.QnaBoardImgResponse;
import com.jgj.byl_process.domain.boards.qna.controller.dto.response.QnaBoardListResponse;
import com.jgj.byl_process.domain.boards.qna.controller.dto.response.QnaBoardModifyResponse;
import com.jgj.byl_process.domain.boards.qna.controller.dto.response.QnaBoardReadResponse;
import com.jgj.byl_process.domain.boards.qna.entity.QnaBoard;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface QnaBoardService {
    void register(List<MultipartFile> imageFileList, QnaBoardRequest qnaBoardRequest);

    List<QnaBoardListResponse> list();

    QnaBoardReadResponse read(Long qnaBoardId);

    List<QnaBoardModifyResponse> modify(Long qnaBoardId, List<MultipartFile> imageFileList, QnaBoardRequest qnaBoardRequest);

    void remove(Long qnaBoardId);

    Long getLastEntityId();

//    List<QnaBoardImgResponse> findQnaBoardImage(Long qnaBoardId);
}
