package com.jgj.byl_process.domain.board.controller;

import com.jgj.byl_process.domain.board.controller.request.NoticeBoardRequest;
import com.jgj.byl_process.domain.board.entity.NoticeBoard;
import com.jgj.byl_process.domain.board.service.NoticeBoardService;
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
        log.info("noticeBoardRegister()");
        noticeBoardService.register(noticeBoardRequest);
    }

    @GetMapping("/list")
    public List<NoticeBoard> noticeBoardList () {
        log.info("noticeBoardList()");

        return noticeBoardService.list();
    }

    @GetMapping("/{noticeBoardId}")
    public NoticeBoard noticeBoardRead(@PathVariable("noticeBoardId") Long noticeBoardId) {
        log.info("noticeBoardRead()");

        return noticeBoardService.read(noticeBoardId);
    }

    @DeleteMapping("/{noticeBoardId}")
    public void noticeBoardRemove(@PathVariable("noticeBoardId") Long noticeBoardId) {
        log.info("noticeBoardRemove()");

        noticeBoardService.remove(noticeBoardId);
    }

    @PutMapping("/{noticeBoardId}")
    public NoticeBoard boardModify(@PathVariable("noticeBoardId") Long noticeBoardId,
                                   @RequestBody NoticeBoardRequest noticeBoardRequest) {

        log.info("noticeBoardModify(): " + noticeBoardRequest + "id: " + noticeBoardId);

        return noticeBoardService.modify(noticeBoardId, noticeBoardRequest);
    }
}
