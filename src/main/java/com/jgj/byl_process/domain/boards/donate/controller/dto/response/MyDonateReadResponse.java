package com.jgj.byl_process.domain.boards.donate.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@Getter
@AllArgsConstructor
@ToString
public class MyDonateReadResponse {

    private Long memberId;
    private Long donateVisitId;
    private String name;
    private String email;
    private String phone;
    private String quantity;
    private String quality;
    private String visitDate;
    private String visitTime;
    private String zipcode;
    private String city;
    private String street;
    private String addressDetail;
    private Date regDate;
    private Date updDate;

}

