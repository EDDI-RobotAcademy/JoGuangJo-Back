package com.jgj.byl_process.domain.boards.notice.service;

import com.jgj.byl_process.domain.boards.notice.entity.NoticeBoard;
import com.jgj.byl_process.domain.boards.notice.service.request.NoticeBoardRequest;

import java.util.List;

public interface NoticeBoardService {
    //public void register(BoardRequest boardRequest);

    void register(NoticeBoardRequest noticeBoardRequest);

    List<NoticeBoard> list();

    NoticeBoard read(Long noticeBoardId);

    void delete(Long noticeBoardId);

    NoticeBoard modify(Long noticeBoardId, NoticeBoardRequest noticeBoardRequest);

    List<NoticeBoard> bigMisstake(Long noticeBoardId, NoticeBoardRequest noticeBoardRequest);

    Long getCount();

    Long getLastEntityId();
}
