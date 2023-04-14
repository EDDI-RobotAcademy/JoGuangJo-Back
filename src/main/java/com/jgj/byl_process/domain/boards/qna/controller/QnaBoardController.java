package com.jgj.byl_process.domain.boards.qna.controller;


import com.jgj.byl_process.domain.boards.qna.controller.dto.request.QnaBoardRequest;
import com.jgj.byl_process.domain.boards.qna.controller.dto.response.QnaBoardImgResponse;
import com.jgj.byl_process.domain.boards.qna.controller.dto.response.QnaBoardListResponse;
import com.jgj.byl_process.domain.boards.qna.controller.dto.response.QnaBoardModifyResponse;
import com.jgj.byl_process.domain.boards.qna.controller.dto.response.QnaBoardReadResponse;
import com.jgj.byl_process.domain.boards.qna.entity.QnaBoard;
import com.jgj.byl_process.domain.boards.qna.service.QnaBoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/qnaBoard")
public class QnaBoardController {
    final private QnaBoardService qnaBoardService;

    public QnaBoardController(QnaBoardService qnaBoardService) {
        this.qnaBoardService = qnaBoardService;
    }

    @PostMapping(value = "/register",
            consumes = { MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public void qnaBoardRegister(
            @RequestPart(value = "imageFileList", required = false) List<MultipartFile> imageFileList,
            @RequestPart(value = "qnaInfo") QnaBoardRequest qnaBoardRequest ) {
        log.info("qnaBoardRegister()");

        if (imageFileList == null) {
            imageFileList = new ArrayList<>();
            log.info("널이냐?");
        }

        qnaBoardService.register(imageFileList, qnaBoardRequest);
    }

    @GetMapping("/list")
    public List<QnaBoardListResponse> qnaBoardList () {
        log.info("QuestionList() 동작");

        return qnaBoardService.list();
    }

    @GetMapping("/{qnaBoardId}")
    public QnaBoardReadResponse questionBoardRead(@PathVariable("qnaBoardId") Long qnaBoardId) {
            System.out.println("read() 동작 : " + qnaBoardId);
            return qnaBoardService.read(qnaBoardId);

    }

@PutMapping(value="/{qnaBoardId}",
        consumes = { MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE })
public List<QnaBoardModifyResponse> qnaBoardModify(
        @PathVariable("qnaBoardId") Long qnaBoardId,
        @RequestPart(value = "imageFileList", required = false) List<MultipartFile> imageFileList,
        @RequestPart(value = "qnaInfo") QnaBoardRequest qnaBoardRequest ) {
    log.info("qnaBoardRegister()");

    if (imageFileList == null) {
        imageFileList = new ArrayList<>();
        log.info("널이냐?");
    }

    return  qnaBoardService.modify(qnaBoardId, imageFileList, qnaBoardRequest);
}

    @DeleteMapping("/{qnaBoardId}")
    public void questionBoardRemove(@PathVariable("qnaBoardId") Long qnaBoardId) {
        log.info("\n" + "questionBoardRemove() 동작 : " + qnaBoardId);

        qnaBoardService.remove(qnaBoardId);
    }
}
