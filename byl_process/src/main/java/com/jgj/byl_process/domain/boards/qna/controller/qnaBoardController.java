package com.jgj.byl_process.domain.boards.qna.controller;


import com.jgj.byl_process.domain.boards.qna.controller.request.qnaBoardRequest;
import com.jgj.byl_process.domain.boards.qna.entity.qnaBoard;
import com.jgj.byl_process.domain.boards.qna.service.qnaBoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/qnaBoard")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class qnaBoardController {
    final private qnaBoardService qnaBoardService;

    public qnaBoardController(qnaBoardService qnaBoardService) {
        this.qnaBoardService = qnaBoardService;
    }
    @PostMapping("/register")
    public void productRegister (@RequestBody qnaBoardRequest qnaBoardRequest) {
        log.info("QuestionBoardRegister() 동작");

        qnaBoardService.register(qnaBoardRequest);
    }
    @GetMapping("/list")
    public List<qnaBoard> productList () {
        log.info("QuestionList() 동작");

        return qnaBoardService.list();
    }

    @GetMapping("/{qnaBoardId}")
    public qnaBoard questionBoardRead(@PathVariable("qnaBoardId") Long qnaBoardId) {
            System.out.println("read() 동작 : " + qnaBoardId);
            return qnaBoardService.read(qnaBoardId);

    }

    @PutMapping("/{qnaBoardId}")
    public qnaBoard questionBoardModify(@PathVariable("qnaBoardId") Long qnaBoardId,
                                        @RequestBody qnaBoardRequest qnaBoardRequest) {
        log.info("\n" + "questionBoardModify() 동작 : " + qnaBoardRequest + "id" + qnaBoardId);

        return qnaBoardService.modify(qnaBoardId, qnaBoardRequest);
    }

    @DeleteMapping("/{qnaBoardId}")
    public void questionBoardRemove(@PathVariable("qnaBoardId") Long qnaBoardId) {
        log.info("\n" + "questionBoardRemove() 동작 : " + qnaBoardId);

        qnaBoardService.remove(qnaBoardId);
    }
}
