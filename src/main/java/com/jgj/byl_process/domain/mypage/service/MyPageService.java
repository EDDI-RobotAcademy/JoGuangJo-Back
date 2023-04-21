package com.jgj.byl_process.domain.mypage.service;

import com.jgj.byl_process.domain.boards.qna.controller.dto.response.QnaBoardListResponse;
import com.jgj.byl_process.domain.mypage.controller.form.*;
import com.jgj.byl_process.domain.mypage.service.response.MemberRollReadResponse;
import com.jgj.byl_process.domain.mypage.service.response.MemberRollListResponse;
import com.jgj.byl_process.domain.mypage.service.response.MyPageResponse;

import java.util.List;

public interface MyPageService {

    List<MyPageResponse> list(String token);

    Boolean register(SaveAddressForm saveAddressForm);

    Boolean passwordCheck(CheckPasswordForm checkPasswordForm);

    Boolean registerModifiedPassword(ModifiedPasswordForm modifiedPasswordForm);

    Boolean registerMemberTypeRequest(MemberTypeRequestDataForm memberTypeRequestDataForm);

    List<MemberRollListResponse> requestlist();

    MemberRollReadResponse readRequest(Long id);

    void rollRequestAccept(MemberTypeRequestCheckForm memberTypeRequestCheckForm);
    void rollRequestReject (RollRequestRejectForm rollRequestRejectForm);
    List<QnaBoardListResponse> findmypost (MemberIdForm memberIdForm);
}