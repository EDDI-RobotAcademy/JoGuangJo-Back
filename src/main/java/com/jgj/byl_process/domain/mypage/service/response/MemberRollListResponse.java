package com.jgj.byl_process.domain.mypage.service.response;

import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class MemberRollListResponse {
    final private Long memberTypeRequestId;
    final private String nickname;
    final private String memberType;
    final private String regDate;
}
