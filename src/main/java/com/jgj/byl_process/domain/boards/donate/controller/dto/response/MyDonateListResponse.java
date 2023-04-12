package com.jgj.byl_process.domain.boards.donate.controller.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Getter
@RequiredArgsConstructor
public class MyDonateListResponse {

    final private Long memberId;
    final private Long donateVisitId;
    final private Date regDate;
    final private Date updDate;

}
