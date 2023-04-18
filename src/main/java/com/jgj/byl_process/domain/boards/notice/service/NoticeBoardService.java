package com.jgj.byl_process.domain.boards.notice.service;

import com.jgj.byl_process.domain.boards.notice.entity.NoticeBoard;
import com.jgj.byl_process.domain.boards.notice.service.request.NoticeBoardRequest;

import java.util.List;

public interface NoticeBoardService {
    //public void register(BoardRequest boardRequest);

    void register(NoticeBoardRequest noticeBoardRequest);

    List<NoticeBoard> list();

    NoticeBoard read(Long boardId);

    void remove(Long boardId);

    NoticeBoard modify(Long boardId, NoticeBoardRequest noticeBoardRequest);

    List<NoticeBoard> bigMisstake(Long boardId, NoticeBoardRequest noticeBoardRequest);

    Long getCount();

    Long getLastEntityId();
}
