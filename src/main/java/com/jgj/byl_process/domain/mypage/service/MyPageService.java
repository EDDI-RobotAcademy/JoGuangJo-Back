package com.jgj.byl_process.domain.mypage.service;

import com.jgj.byl_process.domain.mypage.controller.form.CheckPasswordForm;
import com.jgj.byl_process.domain.mypage.controller.form.MemberTypeRequestDataForm;
import com.jgj.byl_process.domain.mypage.controller.form.ModifiedPassword;
import com.jgj.byl_process.domain.mypage.controller.form.SaveAddressForm;
import com.jgj.byl_process.domain.mypage.service.response.MemberRollReadResponse;
import com.jgj.byl_process.domain.mypage.service.response.MemberRollResponse;
import com.jgj.byl_process.domain.mypage.service.response.MyPageResponse;

import java.util.List;

public interface MyPageService {

    List<MyPageResponse> list(String token);

    Boolean register(SaveAddressForm saveAddressForm);

    Boolean passwordCheck(CheckPasswordForm checkPasswordForm);

    Boolean registerModifiedPassword(ModifiedPassword modifiedPassword);

    Boolean registerMemberTypeRequest(MemberTypeRequestDataForm memberTypeRequestDataForm);

    List<MemberRollResponse> requestlist();

    MemberRollReadResponse readRequest(Long id);
}