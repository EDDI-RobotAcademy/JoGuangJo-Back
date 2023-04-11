//package com.jgj.byl_process.forTest.qnaBoard;
//
//import com.jgj.byl_process.domain.boards.qna.controller.dto.request.QnaBoardRequest;
//import com.jgj.byl_process.domain.boards.qna.controller.dto.request.QnaCommentRequest;
//import com.jgj.byl_process.domain.boards.qna.entity.QnaComment;
//import com.jgj.byl_process.domain.boards.qna.service.QnaBoardService;
//import com.jgj.byl_process.domain.boards.qna.service.QnaCommentService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//
//@SpringBootTest
//@DisplayName("qna comment 게시판에 대한 테스트 코드 - mock 적용")
//public class QnaCommentTest {
//
//    @Autowired
//    @Mock
//    private QnaCommentService qnaCommentService;
//
//    @Autowired
//    @Mock
//    private QnaBoardService qnaBoardService;
//
//        @BeforeEach
//    public void setUp() throws Exception {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void 댓글_등록을_위한_하나의_게시글_등록_테스트() {
//        QnaBoardRequest qnaBoardRequest =
//                new QnaBoardRequest("this is for test", "박태현", "저는 박태현이예요");
//
//        qnaBoardService.register(qnaBoardRequest);
//    }
//    @Test
//    public void 댓글_등록_테스트() {
//        QnaCommentRequest qnaCommentRequest =
//                new QnaCommentRequest("이것은 댓글입니다.", 1L, "닉네임");
//
//        qnaCommentService.qnaCommentRegister(qnaCommentRequest);
//    }
//
//    @Test
//    public void 댓글_리스트_테스트() {
//        System.out.println( "댓글_리스트_테스트입니다." + qnaCommentService.qnaCommentList(1L));
//    }
//
//    @Test
//    public void 댓글_수정_테스트() {
//        QnaComment qnaComment = qnaCommentService.modify(1L, new QnaCommentRequest(
//                "코멘트 변경 테스트", 1L, "박태현으로 변경"));
//
//        System.out.println(qnaComment);
//    }
//
//    @Test
//    public void 댓글_삭제_테스트() {
//        qnaCommentService.remove(1L);
//    }
//}
