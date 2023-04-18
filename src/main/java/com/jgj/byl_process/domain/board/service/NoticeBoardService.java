package com.jgj.byl_process.domain.board.service;

import com.jgj.byl_process.domain.board.controller.request.NoticeBoardRequest;
import com.jgj.byl_process.domain.board.entity.NoticeBoard;

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
