package com.jgj.byl_process.domain.mypage.controller.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberTypeRequestCheckForm {
    Long requestId;
    Long memberId;
    String memberType;

    public String getMemberType() {
        if ("관리자".equals(memberType)) {
            return "Manager";
        } else if ("사업자".equals(memberType)) {
            return "Entrepreneur";
        } else {
            return "General";
        }
    }

}
