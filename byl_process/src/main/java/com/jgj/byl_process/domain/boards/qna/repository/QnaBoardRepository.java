package com.jgj.byl_process.domain.boards.qna.repository;

import com.jgj.byl_process.domain.boards.qna.entity.QnaBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QnaBoardRepository extends JpaRepository<QnaBoard, Long> {

    QnaBoard findFirstByOrderByQnaBoardIdDesc();
}
