package com.jgj.byl_process.domain.member.controller;

import com.jgj.byl_process.domain.member.controller.form.EmailMatchForm;
import com.jgj.byl_process.domain.member.controller.form.EmailPasswordForm;
import com.jgj.byl_process.domain.member.controller.form.MemberLoginForm;
import com.jgj.byl_process.domain.member.controller.form.MemberRegisterForm;
import com.jgj.byl_process.domain.member.service.MemberLoginResponse;
import com.jgj.byl_process.domain.member.service.MemberService;
import com.jgj.byl_process.domain.security.service.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    final private MemberService memberService;
    final private RedisService redisService;

    @PostMapping("/check-email/{email}")
    public Boolean emailValidation(@PathVariable("email") String email) {
        log.info("emailValidation(): " + email);

        return memberService.emailValidation(email);
    }

    @PostMapping("/check-nickName/{nickName}")
    public Boolean memberNickNameValidation(@PathVariable("nickName") String nickName) {
        log.info("memberNicknameDuplicateCheck()" + nickName);

        return memberService.memberNicknameValidation(nickName);
    }

    @PostMapping("/sign-up")
    public Boolean signUp(@RequestBody MemberRegisterForm form) {
        log.info("signUp(): " + form);

        return memberService.signUp(form.toMemberRegisterRequest());
    }

    @PostMapping("/sign-in")
    public MemberLoginResponse signIn(@RequestBody MemberLoginForm form) {
        log.info("signIn(): " + form);

        return memberService.signIn(form.toMemberLoginRequest());
    }

    @PostMapping("/emailMatch")
    public Boolean emailMatchPhone(@Validated @RequestBody EmailMatchForm form, BindingResult bindingResult) {
        log.info("MainFormController#emailMatchPhone: {}", form);
        if (bindingResult.hasFieldErrors()) {
            return false;
        }
        return memberService.emailMatch(form.toEmailMatchRequest());
    }

    @PostMapping("/applyNewPassword")
    public Boolean applyNewPassword(@Validated @RequestBody EmailPasswordForm form) {
        log.info("MainFormController#applyNewPassword: {}", form);

        return memberService.applyNewPassword(form.toEmailPasswordRequest());
    }

    @PostMapping("/logout")
    public void logout(@RequestBody String token) {
        token = token.substring(0, token.length() - 1);
        log.info("logout(): " + token);


        redisService.deleteByKey(token);
    }

    @PostMapping("/resign")
    public void resign(@RequestBody String token) {
        token = token.substring(3, token.length() - 4);
        log.info("resign(): " + token);

        memberService.resign(token);
    }
}