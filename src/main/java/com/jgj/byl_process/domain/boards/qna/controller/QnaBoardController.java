package com.jgj.byl_process.domain.boards.qna.controller;


import com.jgj.byl_process.domain.boards.qna.controller.dto.request.QnaBoardRequest;
import com.jgj.byl_process.domain.boards.qna.entity.QnaBoard;
import com.jgj.byl_process.domain.boards.qna.service.QnaBoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/qnaBoard")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class QnaBoardController {
    final private QnaBoardService qnaBoardService;

    public QnaBoardController(QnaBoardService qnaBoardService) {
        this.qnaBoardService = qnaBoardService;
    }
    @PostMapping("/register")
    public void productRegister (@RequestBody QnaBoardRequest qnaBoardRequest) {
        log.info("QuestionBoardRegister() 동작");

        qnaBoardService.register(qnaBoardRequest);
    }
    @GetMapping("/list")
    public List<QnaBoard> productList () {
        log.info("QuestionList() 동작");

        return qnaBoardService.list();
    }

    @GetMapping("/{qnaBoardId}")
    public QnaBoard questionBoardRead(@PathVariable("qnaBoardId") Long qnaBoardId) {
            System.out.println("read() 동작 : " + qnaBoardId);
            return qnaBoardService.read(qnaBoardId);

    }

    @PutMapping("/{qnaBoardId}")
    public QnaBoard questionBoardModify(@PathVariable("qnaBoardId") Long qnaBoardId,
                                        @RequestBody QnaBoardRequest qnaBoardRequest) {
        log.info("\n" + "questionBoardModify() 동작 : " + qnaBoardRequest + "id" + qnaBoardId);

        return qnaBoardService.modify(qnaBoardId, qnaBoardRequest);
    }

    @DeleteMapping("/{qnaBoardId}")
    public void questionBoardRemove(@PathVariable("qnaBoardId") Long qnaBoardId) {
        log.info("\n" + "questionBoardRemove() 동작 : " + qnaBoardId);

        qnaBoardService.remove(qnaBoardId);
    }
}
