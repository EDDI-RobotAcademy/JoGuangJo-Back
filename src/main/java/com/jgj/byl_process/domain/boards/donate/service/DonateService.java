package com.jgj.byl_process.domain.boards.donate.service;

import com.jgj.byl_process.domain.boards.donate.controller.dto.request.DonateVisitRequest;
import com.jgj.byl_process.domain.boards.donate.controller.dto.response.MyDonateListResponse;
import com.jgj.byl_process.domain.boards.donate.controller.dto.response.MyDonateReadResponse;
import com.jgj.byl_process.domain.member.entity.Member;

import javax.transaction.Transactional;
import java.util.List;

public interface DonateService {


    // 방문수거 페이지에서 방문수거 신청하는 메서드
    @Transactional
    Boolean register(DonateVisitRequest donateVisitRequest);


    // 마이페이지에서 자기 방문수거 기부내역 목록조회하는 메서드
    List<MyDonateListResponse> list(Long memberId);


    // 마이페이지에서 자기 방문수거 기부내역 상세조회하는 메서드
    MyDonateReadResponse read(Long donateVisitId);

}
