package com.jgj.byl_process.domain.mypage.service;

import com.jgj.byl_process.domain.member.entity.Member;
import com.jgj.byl_process.domain.member.service.response.MyPageResponse;

import java.util.List;

public interface MyPageService {

    String list(String token);
}

