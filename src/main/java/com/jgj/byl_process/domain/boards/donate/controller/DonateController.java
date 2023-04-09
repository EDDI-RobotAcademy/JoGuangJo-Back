package com.jgj.byl_process.domain.boards.donate.controller;

import com.jgj.byl_process.domain.boards.donate.controller.form.DonateVisitRegisterForm;
import com.jgj.byl_process.domain.boards.donate.service.response.DonateService;
import com.jgj.byl_process.domain.security.service.RedisService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.RequiredArgsConstructor;

@Slf4j
@RestController
@RequestMapping("/donate")
@RequiredArgsConstructor
public class DonateController {

    final private DonateService donateService;
    final private RedisService redisService;

    @PostMapping("/visit-register")
    public Boolean donateVisitRegister(@RequestBody DonateVisitRegisterForm form) {
        log.info("donateVisitRegister(): " + form);
        return donateService.donateVisitRegister(form.toDonateVisitRegisterRequest());
    }

}
