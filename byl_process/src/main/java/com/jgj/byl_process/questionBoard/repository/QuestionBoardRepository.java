package com.jgj.byl_process.questionBoard.repository;

import com.jgj.byl_process.questionBoard.entity.QuestionBoard;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionBoardRepository extends JpaRepository<QuestionBoard, Long> {
}
