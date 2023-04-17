package com.jgj.byl_process.domain.boards.donate.service.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Getter
@RequiredArgsConstructor
public class DonateListResponse {

    final private Long donateId;
    final private Date regDate;
    final private Date updDate;

}
