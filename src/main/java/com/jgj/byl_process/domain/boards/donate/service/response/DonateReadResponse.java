package com.jgj.byl_process.domain.boards.donate.service.response;

import com.jgj.byl_process.domain.boards.donate.entity.Donate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@Getter
@AllArgsConstructor
@ToString
public class DonateReadResponse {

    private Long memberId;
    private Long donateId;
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


    public static DonateReadResponse fromDonate(Donate donate) {
        return new DonateReadResponse(
                donate.getMemberId(), donate.getDonateId(),
                donate.getName(), donate.getEmail(), donate.getPhone(),
                donate.getQuantity(), donate.getQuality(),
                donate.getVisitDate(), donate.getVisitTime(),
                donate.getZipcode(), donate.getCity(), donate.getStreet(), donate.getAddressDetail()
        );
    }
}

