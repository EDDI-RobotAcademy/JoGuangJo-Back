package com.jgj.byl_process.domain.mypage.service.response;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class MemberRollResponse {
    final private Long memberTypeRequestId;
    final private String nickname;
    final private String memberType;
    final private String regDate;
}
