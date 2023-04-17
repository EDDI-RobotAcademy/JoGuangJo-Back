package com.jgj.byl_process.domain.boards.donate.service;

import com.jgj.byl_process.domain.boards.donate.service.request.DonateModifyRequest;
import com.jgj.byl_process.domain.boards.donate.service.request.DonateRegisterRequest;
import com.jgj.byl_process.domain.boards.donate.service.response.DonateListResponse;
import com.jgj.byl_process.domain.boards.donate.service.response.DonateReadResponse;

import javax.transaction.Transactional;
import java.util.List;

public interface DonateService {


    // 책 기부 페이지에서 기부 신청하는 메서드
    @Transactional
    Boolean register(DonateRegisterRequest donateRegisterRequest);


    // 마이페이지에서 자기 기부내역 목록조회하는 메서드
    List<DonateListResponse> list(Long memberId);


    // 마이페이지에서 자기 기부내역 상세조회하는 메서드
    DonateReadResponse read(Long donateId);


    // 마이페이지에서 자기 기부내역 수정하는 메서드
    Boolean modify(DonateModifyRequest donateModifyRequest);


    // 마이페이지에서 자기 기부내역 삭제하는 메서드
    Boolean delete(Long donateId);


}
