package com.jgj.byl_process.domain.member.service;

import com.jgj.byl_process.domain.member.service.request.EmailMatchRequest;
import com.jgj.byl_process.domain.member.service.request.EmailPasswordRequest;
import com.jgj.byl_process.domain.member.service.request.MemberLoginRequest;
import com.jgj.byl_process.domain.member.service.request.MemberRegisterRequest;

public interface MemberService {
    Boolean emailValidation(String email);
    Boolean signUp(MemberRegisterRequest memberRegisterRequest);
    String signIn(MemberLoginRequest memberLoginRequest);

    Boolean applyNewPassword(EmailPasswordRequest toEmailPasswordRequest);

    Boolean emailMatch(EmailMatchRequest toEmailMatchRequest);
}