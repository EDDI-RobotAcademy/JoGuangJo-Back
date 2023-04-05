package com.jgj.byl_process.domain.boards.qna.controller;

import com.jgj.byl_process.domain.boards.qna.controller.request.QnaCommentRequest;
import com.jgj.byl_process.domain.boards.qna.entity.QnaComment;
import com.jgj.byl_process.domain.boards.qna.service.QnaCommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/qnaBoard/qnaComment")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class QnaCommentController {

    @Autowired
    QnaCommentService qnaCommentService;

    @GetMapping("/{qnaBoardId}")
    public List<QnaComment> qnaCommentList(@PathVariable("qnaBoardId") Long qnaBoardId) {
        log.info("qnaCommentList() 동작");

        return qnaCommentService.qnaCommentList(qnaBoardId);
    }


    @PostMapping("/register")
    public void qnaCommentRegister(@RequestBody QnaCommentRequest qnaCommentRequest) {
        log.info("qnaCommentRegister() 해당 게시물 아이디 : " + qnaCommentRequest.getQnaBoardId());
        log.info(qnaCommentRequest.getComment());
        log.info(qnaCommentRequest.getWriter());

        qnaCommentService.qnaCommentRegister(qnaCommentRequest);
    }



}
