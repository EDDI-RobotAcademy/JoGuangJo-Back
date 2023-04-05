package com.jgj.byl_process.domain.mypage.service;

import com.jgj.byl_process.domain.mypage.service.response.MyPageResponse;

import java.util.List;

public interface MyPageService {

    List<MyPageResponse> list(String token);
}

