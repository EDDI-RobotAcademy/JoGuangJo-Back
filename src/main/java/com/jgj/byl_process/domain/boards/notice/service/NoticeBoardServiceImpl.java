package com.jgj.byl_process.domain.boards.notice.service;

import com.jgj.byl_process.domain.boards.notice.service.request.NoticeBoardRequest;
import com.jgj.byl_process.domain.boards.notice.entity.NoticeBoard;
import com.jgj.byl_process.domain.boards.notice.repository.NoticeBoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeBoardServiceImpl implements NoticeBoardService {

    final private NoticeBoardRepository noticeBoardRepository;

    /*
    @Override
    public void register(BoardRequest boardRequest) {
        Board board = new Board();
        board.setTitle(boardRequest.getTitle());
        board.setWriter(boardRequest.getWriter());
        board.setContent(boardRequest.getContent());

        boardRepository.save(board);
    }
     */

    public void register(NoticeBoardRequest noticeBoardRequest) {
        NoticeBoard noticeBoard = new NoticeBoard();
        noticeBoard.setTitle(noticeBoardRequest.getTitle());
        noticeBoard.setWriter(noticeBoardRequest.getWriter());
        noticeBoard.setContent(noticeBoardRequest.getContent());

        noticeBoardRepository.save(noticeBoard);
    }

    @Override
    public List<NoticeBoard> list() {
        System.out.println(
        "noticeBoardRepository.findAll" + noticeBoardRepository.findAll(Sort.by(Sort.Direction.DESC, "noticeBoardId"))
        );
        return noticeBoardRepository.findAll(Sort.by(Sort.Direction.DESC, "noticeBoardId"));
    }

    @Override
    public NoticeBoard read(Long noticeBoardId) {
        Optional<NoticeBoard> maybeBoard = noticeBoardRepository.findById(noticeBoardId);

        if (maybeBoard.isEmpty()) {
            log.info("해당 noticeBoardId로 noticeBoard 데이터를 찾지 못했습니다: " + noticeBoardId);
            return null;
        }

        return maybeBoard.get();
    }

    @Override
    public NoticeBoard modify(Long noticeBoardId, NoticeBoardRequest noticeBoardRequest) {
        Optional<NoticeBoard> maybeBoard = noticeBoardRepository.findById(noticeBoardId);

        if (maybeBoard.isEmpty()) {
            log.info("해당 noticeBoardId로 noticeBoard 데이터를 찾지 못했습니다: " + noticeBoardId);
            return null;
        }

        NoticeBoard noticeBoard = maybeBoard.get();
        noticeBoard.setTitle(noticeBoardRequest.getTitle());
        noticeBoard.setContent(noticeBoardRequest.getContent());

        noticeBoardRepository.save(noticeBoard);

        return noticeBoard;
    }

    @Override
    public void delete(Long noticeBoardId) {
        noticeBoardRepository.deleteById(noticeBoardId);
    }



    @Override
    public List<NoticeBoard> bigMisstake(Long noticeBoardId, NoticeBoardRequest noticeBoardRequest) {
        return noticeBoardRepository.findByNoticeBoardIdAndWriter(noticeBoardId, noticeBoardRequest.getWriter());
    }

    @Override
    public Long getCount() {
        return noticeBoardRepository.countBy();
    }

    @Override
    public Long getLastEntityId() {
        NoticeBoard noticeBoard = noticeBoardRepository.findFirstByOrderByNoticeBoardIdDesc();
        return noticeBoard.getNoticeBoardId();
    }
}
