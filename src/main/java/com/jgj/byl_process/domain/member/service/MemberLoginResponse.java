package com.jgj.byl_process.domain.member.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class MemberLoginResponse {
    private final String token;
    private final Long id;
    private final String nickName;
    
/* (박지영) 로그인하면 사용자의 memberType 도 알려주기 위해 추가함
    Member 도메인의 Role 엔티티 에 있는 memberType 이 진짜 role 이기 때문에 이걸로 가져옵니다 */
    private final String memberType;


}
