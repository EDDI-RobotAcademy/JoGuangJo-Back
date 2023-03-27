package com.jgj.byl_process.questionBoard.controller;


import com.jgj.byl_process.questionBoard.controller.request.QuestionBoardRequest;
import com.jgj.byl_process.questionBoard.entity.QuestionBoard;
import com.jgj.byl_process.questionBoard.service.QuestionBoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/questionBoard")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class QuestionBoardController {
    final private QuestionBoardService questionBoardService;

    public QuestionBoardController(QuestionBoardService questionBoardService) {
        this.questionBoardService = questionBoardService;
    }
    @PostMapping("/register")
    public void productRegister (@RequestBody QuestionBoardRequest QuestionBoardRequest) {
        log.info("QuestionBoardRegister() 동작");

        questionBoardService.register(QuestionBoardRequest);
    }
    @GetMapping("/list")
    public List<QuestionBoard> productList () {
        log.info("QuestionList() 동작");

        return questionBoardService.list();
    }

    @GetMapping("/{questionBoardId}")
    public QuestionBoard questionBoardRead(@PathVariable("questionBoardId") Long questionBoardId) {
            System.out.println("read() 동작 : " + questionBoardId);
            return questionBoardService.read(questionBoardId);

    }

    @PutMapping("/{questionBoardId}")
    public QuestionBoard questionBoardModify(@PathVariable("questionBoardId") Long questionBoardId,
                                                                                                 @RequestBody QuestionBoardRequest questionBoardRequest) {
        log.info("\n" + "questionBoardModify() 동작 : " + questionBoardRequest + "id" + questionBoardId);

        return questionBoardService.modify(questionBoardId, questionBoardRequest);
    }

    @DeleteMapping("/{boardId}")
    public void questionBoardRemove(@PathVariable("boardId") Long questionBoardId) {
        log.info("\n" + "questionBoardRemove() 동작 : " + questionBoardId);

        questionBoardService.remove(questionBoardId);
    }
}
