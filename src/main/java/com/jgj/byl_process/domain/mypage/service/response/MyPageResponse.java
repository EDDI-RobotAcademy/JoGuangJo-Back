package com.jgj.byl_process.domain.mypage.service.response;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
//@Value 이 어노테이션으로 퉁 칠수있음
public class MyPageResponse {
    private Long id;
    private String email;
    private String city;
    private String street;
    private String addressDetail;
    private String zipcode;
    private String authenticationType;
}