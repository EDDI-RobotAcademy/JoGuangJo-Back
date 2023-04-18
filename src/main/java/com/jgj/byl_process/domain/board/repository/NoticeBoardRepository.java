package com.jgj.byl_process.domain.board.repository;

import com.jgj.byl_process.domain.board.entity.NoticeBoard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeBoardRepository extends JpaRepository<NoticeBoard, Long> {

    Long countBy();

    List<NoticeBoard> findByNoticeBoardIdAndWriter(Long noticeBoardId, String writer);

    NoticeBoard findFirstByOrderByNoticeBoardIdDesc();
}
