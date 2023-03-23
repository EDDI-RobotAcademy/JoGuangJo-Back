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
    final private QuestionBoardService QuestionBoardService;

    public QuestionBoardController(QuestionBoardService questionBoardService) {
        this.QuestionBoardService = questionBoardService;
    }
    @PostMapping("/register")
    public void productRegister (@RequestBody QuestionBoardRequest QuestionBoardRequest) {
        log.info("QuestionBoardRegister() 동작");

        QuestionBoardService.register(QuestionBoardRequest);
    }
}
