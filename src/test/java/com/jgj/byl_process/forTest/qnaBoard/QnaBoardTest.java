//package com.jgj.byl_process.forTest.qnaBoard;
//
//import com.jgj.byl_process.domain.boards.qna.controller.dto.request.QnaBoardRequest;
//import com.jgj.byl_process.domain.boards.qna.entity.QnaBoard;
//import com.jgj.byl_process.domain.boards.qna.service.QnaBoardService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//@DisplayName("qna 게시판에 대한 테스트 코드 - mock 적용")
//public class QnaBoardTest {
//
//    @Mock
//    private QnaBoardService qnaBoardService;
//
//    @BeforeEach
//    public void setUp() throws Exception {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void 게시글_등록_테스트() {
//        QnaBoardRequest qnaBoardRequest =
//                new QnaBoardRequest("this is for test", "박태현", "저는 박태현이예요");
//
//        qnaBoardService.register(qnaBoardRequest);
//    }
//
//    @Test
//    public void 게시글_리스트_테스트() {
//        System.out.println("게시글_리스트_테스트입니다." + qnaBoardService.list());
//    }
//
//    @Test
//    public void 게시글_읽기_테스트() {
//        QnaBoard qnaBoard = qnaBoardService.read(1L);
//        System.out.println("게시글_읽기_테스트입니다." + qnaBoard);
//    }
//
//    @Test
//    public void 게시물_수정_테스트() {
//        QnaBoard qnaBoard = qnaBoardService.modify(1L, new QnaBoardRequest(
//                "this is for modification test", "북유럽", "내용을 변경해보자"));
//
//        System.out.println(qnaBoard);
//    }
//
//    @Test
//    public void 게시물_삭제_테스트() {
//        qnaBoardService.remove(1L);
//        qnaBoardService.read(1L);
//    }
//
//    @Test
//    public void 게시판_구동_전체_테스트() {
//        QnaBoardRequest QnaBoardRequest =
//                new QnaBoardRequest("등록되냐?", "박태현", "등록 되나요?");
//
//        qnaBoardService.register(QnaBoardRequest);
//        Long lastQnaBoardId = qnaBoardService.getLastEntityId();
//
//        System.out.println("초기 등록" + qnaBoardService.read(lastQnaBoardId));
//
//        qnaBoardService.modify(lastQnaBoardId, new QnaBoardRequest(
//                "변경 됩니까?", "박태현", "변경 됐어요"
//        ));
//
//        System.out.println("수정 후 : " + qnaBoardService.read(lastQnaBoardId));
//
//        qnaBoardService.remove(lastQnaBoardId);
//
//        System.out.println("삭제 후 : " + qnaBoardService.read(lastQnaBoardId));
//    }
//
//
//}
