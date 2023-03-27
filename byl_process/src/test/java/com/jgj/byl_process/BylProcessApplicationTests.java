package com.jgj.byl_process;

import com.jgj.byl_process.domain.boards.qna.controller.request.qnaBoardRequest;
import com.jgj.byl_process.domain.boards.qna.entity.qnaBoard;
import com.jgj.byl_process.domain.boards.qna.service.qnaBoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BylProcessApplicationTests {

	@Autowired
	private qnaBoardService qnaBoardService;

	@Test
	public void 게시글_등록_테스트() {
		qnaBoardRequest qnaBoardRequest =
				new qnaBoardRequest("test의 test", "박태현", "테스트 내용임");

		qnaBoardService.register(qnaBoardRequest);
	}

	@Test
	public void 게시글_리스트_테스트() {
		qnaBoard qnaBoard = qnaBoardService.read(3L);
	}

	@Test
	public void 게시글_읽기_테스트() {
		qnaBoard qnaBoard = qnaBoardService.read(3L);
		System.out.println(qnaBoard);
	}

	@Test
	public void 게시물_수정_테스트() {
		qnaBoard qnaBoard = qnaBoardService.modify(3L, new qnaBoardRequest(
				"변경됩니까", "북유럽", "내용 변경해보자"));

		System.out.println(qnaBoard);
	}

	@Test
	public void 게시물_삭제_테스트() {
		qnaBoardService.remove(3L);
		qnaBoardService.read(3L);
	}

	@Test
	public void 게시판_구동_전체_테스트() {
		qnaBoardRequest QnaBoardRequest =
				new qnaBoardRequest("등록되냐?", "박태현", "등록 되나요?");

		qnaBoardService.register(QnaBoardRequest);
		Long lastQnaBoardId = qnaBoardService.getLastEntityId();

		System.out.println("초기 등록" + qnaBoardService.read(lastQnaBoardId));

		qnaBoardService.modify(lastQnaBoardId, new qnaBoardRequest(
				"변경 됩니까?", "박태현", "변경 됐어요"
		));

		System.out.println("수정 후 : " + qnaBoardService.read(lastQnaBoardId));

		qnaBoardService.remove(lastQnaBoardId);

		System.out.println("삭제 후 : " + qnaBoardService.read(lastQnaBoardId));
	}
}
