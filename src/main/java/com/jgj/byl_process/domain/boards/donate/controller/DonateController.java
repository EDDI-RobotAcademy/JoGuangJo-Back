package com.jgj.byl_process.domain.boards.donate.controller;

import com.jgj.byl_process.domain.boards.donate.service.DonateService;
import com.jgj.byl_process.domain.boards.donate.service.request.DonateModifyRequest;
import com.jgj.byl_process.domain.boards.donate.service.request.DonateRegisterRequest;
import com.jgj.byl_process.domain.boards.donate.service.response.DonateListResponse;
import com.jgj.byl_process.domain.boards.donate.service.response.DonateReadResponse;

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

    // 책 기부 페이지에서 기부신청하는 메서드
    @PostMapping("/register")
    public Boolean donateRegister(@RequestBody DonateRegisterRequest donateRegisterRequest) {
        log.info("donateRegister() 동작: " + donateRegisterRequest);
        return donateService.register(donateRegisterRequest);
    }

    // 마이페이지에서 자기 기부내역 목록조회하는 메서드
    @GetMapping("/list")
    public List<DonateListResponse> donateList(@RequestParam("memberId") Long memberId) {
        log.info("donateList() 동작: " + memberId);
        return donateService.list(memberId);
    }

    // 마이페이지에서 자기 기부내역 상세조회하는 메서드
    @GetMapping("/read/{donateId}")
    public DonateReadResponse donateRead(@PathVariable("donateId") Long donateId) {
        log.info("donateRead() 동작: " + donateId);
        return donateService.read(donateId);
    }

    // 마이페이지에서 자기 기부내역 수정하는 메서드
    @PutMapping("/modify/{donateId}")
    public Boolean donateModify(@RequestBody DonateModifyRequest donateModifyRequest) {
        log.info("deliveryModify(): "+ donateModifyRequest);

        return donateService.modify(donateModifyRequest);
    }

    // 마이페이지에서 자기 기부내역 삭제하는 메서드
    @DeleteMapping("/delete/{donateId}")
    public Boolean donateDelete(@PathVariable("donateId") Long donateId) {
        log.info("donateDelete() 동작: "+ donateId);
        return donateService.delete(donateId);
    }




}
