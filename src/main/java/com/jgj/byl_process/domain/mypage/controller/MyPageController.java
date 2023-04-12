package com.jgj.byl_process.domain.mypage.controller;

import com.jgj.byl_process.domain.mypage.controller.form.CheckPasswordForm;
import com.jgj.byl_process.domain.mypage.controller.form.MemberTypeRequestDataForm;
import com.jgj.byl_process.domain.mypage.controller.form.ModifiedPassword;
import com.jgj.byl_process.domain.mypage.controller.form.SaveAddressForm;
import com.jgj.byl_process.domain.mypage.service.MyPageService;
import com.jgj.byl_process.domain.mypage.service.response.MemberRollResponse;
import com.jgj.byl_process.domain.mypage.service.response.MyPageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MyPageController {

    final private MyPageService myPageService;

    @PostMapping("/list")
    public List<MyPageResponse> memberInfoList(@RequestBody String token) {
        log.info("memberInfoList(): " + token);
        token = token.substring(0, token.length() - 1);

        return myPageService.list(token);
    }

    @PostMapping("/saveAddress")
    public Boolean saveAddress(@RequestBody SaveAddressForm saveAddressForm) {
        log.info(("saveAddress(): " + saveAddressForm.toString()));

        return myPageService.register(saveAddressForm);
    }

    @PostMapping("/passwordCheck")
    public Boolean passwordCheck(@RequestBody CheckPasswordForm checkPasswordForm) {
        log.info("passwordCheck(): " + checkPasswordForm.getId());
        log.info("passwordCheck(): " + checkPasswordForm.getPassword());


        return myPageService.passwordCheck(checkPasswordForm);
    }

    @PostMapping("/registerModifiedPassword")
    public Boolean registerModifiedPassword(@RequestBody ModifiedPassword modifiedPassword) {
        log.info("passwordCheck(): " + modifiedPassword.getId());
        log.info("passwordCheck(): " + modifiedPassword.getPassword());

        return myPageService.registerModifiedPassword(modifiedPassword);
    }

    @PostMapping("/memberTypeRequest")
    public Boolean memberTypeRequest(@RequestBody MemberTypeRequestDataForm memberTypeRequestDataForm) {
        log.info("아이디(): " + memberTypeRequestDataForm.getMemberId());
        log.info("타입(): " + memberTypeRequestDataForm.getMemberType());
        log.info("메시지(): " + memberTypeRequestDataForm.getMessage());

        myPageService.registerMemberTypeRequest(memberTypeRequestDataForm);
        return false;
    }

    @GetMapping("/memberTypeRequestList")
    public List<MemberRollResponse> memberTypeRequestList() {
        log.info("memberTypeRequestList() 실행");

        return myPageService.requestlist();
    }
}
