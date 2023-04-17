package com.jgj.byl_process.domain.boards.donate.service;

import com.jgj.byl_process.domain.boards.donate.service.request.DonateModifyRequest;
import com.jgj.byl_process.domain.boards.donate.service.request.DonateRegisterRequest;
import com.jgj.byl_process.domain.boards.donate.service.response.DonateListResponse;
import com.jgj.byl_process.domain.boards.donate.service.response.DonateReadResponse;

import javax.transaction.Transactional;
import java.util.List;

public interface DonateService {


    // 방문수거 페이지에서 방문수거 신청하는 메서드
    @Transactional
    Boolean register(DonateRegisterRequest donateRegisterRequest);


    // 마이페이지에서 자기 방문수거 기부내역 목록조회하는 메서드
    List<DonateListResponse> list(Long memberId);


    // 마이페이지에서 자기 방문수거 기부내역 상세조회하는 메서드
    DonateReadResponse read(Long donateId);

    Boolean modify(DonateModifyRequest donateModifyRequest);

    Boolean delete(Long donateId);


}
