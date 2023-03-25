package com.jgj.byl_process;

import com.jgj.byl_process.domain.board.controller.request.BoardRequest;
import com.jgj.byl_process.questionBoard.controller.request.QuestionBoardRequest;
import com.jgj.byl_process.questionBoard.entity.QuestionBoard;
import com.jgj.byl_process.questionBoard.service.QuestionBoardService;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BylProcessApplicationTests {

	@Autowired
	private QuestionBoardService questionBoardService;

	@Test
	public void 게시글_등록_테스트() {
		QuestionBoardRequest questionBoardRequest =
				new QuestionBoardRequest("test의 test", "박태현", "테스트 내용임");

		questionBoardService.register(questionBoardRequest);
	}

	@Test
	public void 게시글_리스트_테스트() {
		QuestionBoard questionBoard = questionBoardService.read(3L);
	}

	@Test
	public void 게시글_읽기_테스트() {
		QuestionBoard questionBoard = questionBoardService.read(3L);
		System.out.println(questionBoard);
	}

	@Test
	public void 게시물_수정_테스트() {
		QuestionBoard questionBoard = questionBoardService.modify(3L, new QuestionBoardRequest(
				"변경됩니까", "북유럽", "내용 변경해보자"));

		System.out.println(questionBoard);
	}

	@Test
	public void 게시물_삭제_테스트() {
		questionBoardService.remove(3L);
		questionBoardService.read(3L);
	}

	@Test
	public void 게시판_구동_전체_테스트() {
		QuestionBoardRequest questionBoardRequest =
				new QuestionBoardRequest("등록되냐?", "박태현", "등록 되나요?");

		questionBoardService.register(questionBoardRequest);
		Long lastQuestionBoardId = questionBoardService.getLastEntityId();

		System.out.println("초기 등록" + questionBoardService.read(lastQuestionBoardId));

		questionBoardService.modify(lastQuestionBoardId, new QuestionBoardRequest(
				"변경 됩니까?", "박태현", "변경 됐어요"
		));

		System.out.println("수정 후 : " + questionBoardService.read(lastQuestionBoardId));

		questionBoardService.remove(lastQuestionBoardId);

		System.out.println("삭제 후 : " + questionBoardService.read(lastQuestionBoardId));
	}
}
