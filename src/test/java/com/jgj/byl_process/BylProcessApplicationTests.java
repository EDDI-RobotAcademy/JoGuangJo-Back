package com.jgj.byl_process;

import com.jgj.byl_process.domain.boards.qna.controller.request.QnaBoardRequest;
import com.jgj.byl_process.domain.boards.qna.controller.request.QnaCommentRequest;
import com.jgj.byl_process.domain.boards.qna.entity.QnaBoard;
import com.jgj.byl_process.domain.boards.qna.service.QnaBoardService;
import com.jgj.byl_process.domain.boards.qna.service.QnaCommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BylProcessApplicationTests {

    @Autowired
    private QnaCommentService qnaCommentService;
    
    @Test
    public void 게시판_댓글_리스트_테스트() {
        System.out.println(qnaCommentService.qnaCommentList(4L));
    }
}
