package com.jgj.byl_process.domain.boards.donate.service.request;

import com.jgj.byl_process.domain.boards.donate.entity.Donate;
import com.jgj.byl_process.domain.member.entity.Member;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DonateRegisterRequest {
    private Long memberId;
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

    public Donate toDonate(Member member) {
        return new Donate(
                member, this.getName(), this.getEmail(), this.getPhone(),
                this.getQuantity(), this.getQuality(), this.getVisitDate(), this.getVisitTime(),
                this.getZipcode(), this.getCity(), this.getStreet(), this.getAddressDetail()
        );
    }

}
