package com.jgj.byl_process.domain.boards.donate.controller.dto.request;

import com.jgj.byl_process.domain.boards.donate.entity.Donate;
import com.jgj.byl_process.domain.member.entity.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class DonateVisitRequest {

    final private Long memberId;
    final private String name;
    final private String email;
    final private String phone;
    final private String quantity;
    final private String quality;
    final private String visitDate;
    final private String visitTime;
    final private String zipcode;
    final private String city;
    final private String street;
    final private String addressDetail;

    public Donate toDonate(Member member) {
        return new Donate(member, this.getName(), this.getEmail(), this.getPhone(), this.getQuantity(), this.getQuality(), this.getVisitDate(), this.getVisitTime(), this.getZipcode(), this.getCity(), this.getStreet(), this.getAddressDetail());
    }

}
