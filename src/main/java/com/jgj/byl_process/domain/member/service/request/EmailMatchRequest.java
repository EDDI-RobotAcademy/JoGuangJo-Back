package com.jgj.byl_process.domain.member.service.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class EmailMatchRequest {

    private final String email;
}