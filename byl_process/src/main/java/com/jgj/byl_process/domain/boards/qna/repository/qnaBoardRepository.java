package com.jgj.byl_process.domain.boards.qna.repository;

import com.jgj.byl_process.domain.boards.qna.entity.qnaBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface qnaBoardRepository extends JpaRepository<qnaBoard, Long> {

    qnaBoard findFirstByOrderByQnaBoardIdDesc();
}
