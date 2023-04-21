package com.jgj.byl_process.domain.boards.qna.repository;

import com.jgj.byl_process.domain.boards.qna.entity.QnaBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QnaBoardRepository extends JpaRepository<QnaBoard, Long> {

    QnaBoard findFirstByOrderByQnaBoardIdDesc();

    @Query("SELECT q FROM QnaBoard q WHERE q.writer = :writer")
    List<QnaBoard> findByWriter(@Param("writer") String writer);
}
