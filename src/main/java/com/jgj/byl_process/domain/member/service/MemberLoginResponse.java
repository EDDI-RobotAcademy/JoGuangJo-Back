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
}
