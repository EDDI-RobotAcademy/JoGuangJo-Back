package com.jgj.byl_process.domain.member.controller.form;

import com.jgj.byl_process.domain.member.service.request.MemberRegisterRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberRegisterForm {

    private String email;
    private String password;
    private String city;
    private String street;
    private String addressDetail;
    private String zipcode;

    private String nickName;

    public MemberRegisterRequest toMemberRegisterRequest () {
        return new MemberRegisterRequest(email, password, nickName, "", "", "", "");
    }
}