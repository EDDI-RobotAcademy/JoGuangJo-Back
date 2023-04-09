package com.jgj.byl_process.domain.boards.donate.service.response;

import com.jgj.byl_process.domain.boards.donate.controller.dto.request.DonateVisitRegisterRequest;

import javax.transaction.Transactional;

public interface DonateService {

    @Transactional
    Boolean donateVisitRegister(DonateVisitRegisterRequest donateVisitRegisterRequest);

}
