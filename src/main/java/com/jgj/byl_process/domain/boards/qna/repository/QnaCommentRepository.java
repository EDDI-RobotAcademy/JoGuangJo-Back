package com.jgj.byl_process.domain.boards.qna.repository;

import com.jgj.byl_process.domain.boards.qna.entity.QnaComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QnaCommentRepository extends JpaRepository<QnaComment,Long> {

    @Query("select qc from QnaComment qc join fetch qc.qnaBoard qb where qb.qnaBoardId = :qnaBoardId")
    List<QnaComment> findAllCommentByQnaBoardId(Long qnaBoardId);
}
