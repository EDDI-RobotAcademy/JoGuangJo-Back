package com.jgj.byl_process.domain.boards.notice.controller;

import com.jgj.byl_process.domain.boards.notice.service.request.NoticeBoardRequest;
import com.jgj.byl_process.domain.boards.notice.entity.NoticeBoard;
import com.jgj.byl_process.domain.boards.notice.service.NoticeBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeBoardController {

    final private NoticeBoardService noticeBoardService;

    @PostMapping("/register")
    public void noticeBoardRegister (@RequestBody NoticeBoardRequest noticeBoardRequest) {
        log.info("noticeBoardRegister() 동작: " + noticeBoardRequest);
        noticeBoardService.register(noticeBoardRequest);
    }

    @GetMapping("/list")
    public List<NoticeBoard> noticeBoardList () {
        log.info("noticeBoardList() 동작: ");

        return noticeBoardService.list();
    }

    @GetMapping("/read/{noticeBoardId}")
    public NoticeBoard noticeBoardRead(@PathVariable("noticeBoardId") Long noticeBoardId) {
        log.info("noticeBoardRead() 동작: " + noticeBoardId);

        return noticeBoardService.read(noticeBoardId);
    }

    @PutMapping("/modify/{noticeBoardId}")
    public NoticeBoard noticeBoardModify(@PathVariable("noticeBoardId") Long noticeBoardId,
                                        @RequestBody NoticeBoardRequest noticeBoardRequest) {

        log.info("noticeBoardModify() 동작: " + noticeBoardRequest + "noticeBoardId: " + noticeBoardId);

        return noticeBoardService.modify(noticeBoardId, noticeBoardRequest);
    }

    @DeleteMapping("/delete/{noticeBoardId}")
    public void noticeBoardDelete(@PathVariable("noticeBoardId") Long noticeBoardId) {
        log.info("noticeBoardDelete() 동작: " + noticeBoardId);

        noticeBoardService.delete(noticeBoardId);
    }

}
