package com.jgj.byl_process.domain.mypage.controller;

import com.jgj.byl_process.domain.mypage.service.MyPageService;
import com.jgj.byl_process.domain.mypage.service.response.MyPageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MyPageController {

    final private MyPageService myPageService;

    @PostMapping("/list")
    public List<MyPageResponse> memberInfoList(@RequestBody String token) {
        log.info("memberInfoList(): " + token);
        token = token.substring(0, token.length() - 1);

        return myPageService.list(token);
    }
}
