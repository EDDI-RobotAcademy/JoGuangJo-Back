package com.jgj.byl_process.domain.boards.donate.controller;

import com.jgj.byl_process.domain.boards.donate.controller.dto.request.DonateVisitRequest;
import com.jgj.byl_process.domain.boards.donate.controller.dto.response.MyDonateListResponse;
import com.jgj.byl_process.domain.boards.donate.controller.dto.response.MyDonateReadResponse;
import com.jgj.byl_process.domain.boards.donate.service.DonateService;
import com.jgj.byl_process.domain.security.service.RedisService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/donate")
@RequiredArgsConstructor
public class DonateController {

    final private DonateService donateService;
    final private RedisService redisService;

    //방문수거 페이지에서 방문수거 기부신청하는 메서드
    @PostMapping("/visit/register")
    public Boolean donateVisitRegister(@RequestBody DonateVisitRequest donateVisitRequest) {
        log.info("donateVisitRegister() 동작: " + donateVisitRequest);
        return donateService.register(donateVisitRequest);
    }

    // 마이페이지에서 자기 방문수거 기부내역 목록조회하는 메서드
    @GetMapping("/myDonateList")
    public List<MyDonateListResponse> myDonateList(@RequestParam("memberId") Long memberId) {
        log.info("myDonateList() 동작: " + memberId);
        return donateService.list(memberId);
    }

    // 마이페이지에서 자기 방문수거 기부내역 상세조회하는 메서드
    @GetMapping("/myDonateRead/{donateVisitId}")
    public MyDonateReadResponse myDonateRead(@PathVariable("donateVisitId") Long donateVisitId) {
        log.info("myDonateRead() 동작: " + donateVisitId);
        return donateService.read(donateVisitId);
    }

}
