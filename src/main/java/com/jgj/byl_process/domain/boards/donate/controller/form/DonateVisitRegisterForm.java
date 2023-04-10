package com.jgj.byl_process.domain.boards.donate.controller.form;

import com.jgj.byl_process.domain.boards.donate.controller.dto.request.DonateVisitRegisterRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class DonateVisitRegisterForm {

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


    public DonateVisitRegisterRequest toDonateVisitRegisterRequest () {
        return new DonateVisitRegisterRequest(
                name, email, phone, quantity, quality, visitDate, visitTime, zipcode, city, street, addressDetail
        );
    }
}
