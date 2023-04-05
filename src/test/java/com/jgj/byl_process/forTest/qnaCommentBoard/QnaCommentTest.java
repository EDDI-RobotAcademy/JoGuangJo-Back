package com.jgj.byl_process.forTest.qnaCommentBoard;

import com.jgj.byl_process.domain.boards.qna.controller.request.QnaCommentRequest;
import com.jgj.byl_process.domain.boards.qna.service.QnaCommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class QnaCommentTest {

    @Autowired
    private QnaCommentService qnaCommentService;

//    @Test
//    public void 게시판_댓글_등록_테스트() {
//        QnaCommentRequest qnaCommentRequest =
//                new QnaCommentRequest("4번 게시물 두번째 댓글도 등록 되니?", 4L, "작성자임");
//
//        qnaCommentService.qnaCommentRegister(qnaCommentRequest);
//    }

//    @Test
//    public void 게시판_댓글_리스트_테스트() {
//        System.out.println(qnaCommentService.qnaCommentList(4L));
//    }


}
