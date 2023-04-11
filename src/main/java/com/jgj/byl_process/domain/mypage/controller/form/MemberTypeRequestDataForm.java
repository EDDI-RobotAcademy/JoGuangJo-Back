package com.jgj.byl_process.domain.mypage.controller.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberTypeRequestDataForm {
    Long MemberId;
    String MemberType;
    String Message;
}
