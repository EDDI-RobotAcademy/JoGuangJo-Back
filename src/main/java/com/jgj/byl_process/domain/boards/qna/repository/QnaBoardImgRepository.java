package com.jgj.byl_process.domain.boards.qna.repository;

import com.jgj.byl_process.domain.boards.product.entity.ImageResource;
import com.jgj.byl_process.domain.boards.qna.entity.QnaBoardImgResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QnaBoardImgRepository extends JpaRepository<QnaBoardImgResource, Long> {

    @Query("select i from QnaBoardImgResource i join i.qnaBoard q where q.qnaBoardId = :qnaBoardId")
    List<QnaBoardImgResource> findImagePathByQnaBoardId(Long qnaBoardId);

}
