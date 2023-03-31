package com.jgj.byl_process.domain.member.service.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MyPageResponse {
    private Long id;

    private String email;

    private String city;

    private String street;

    private String addressDetail;

    private String zipcode;

    private String authenticationType;
}
