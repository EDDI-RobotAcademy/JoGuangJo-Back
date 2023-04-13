package com.jgj.byl_process.domain.mypage.service.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class MemberRollReadResponse {
    final private Long memberTypeRequestId;
    final private Long memberId;
    final private String nickname;
    final private String memberType;
    final private String message;
    final private String regDate;
}
