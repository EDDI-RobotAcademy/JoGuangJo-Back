package com.jgj.byl_process.domain.mypage.controller;

import com.jgj.byl_process.domain.boards.qna.controller.dto.response.QnaBoardListResponse;
import com.jgj.byl_process.domain.mypage.controller.form.*;
import com.jgj.byl_process.domain.mypage.service.MyPageService;
import com.jgj.byl_process.domain.mypage.service.response.MemberRollListResponse;
import com.jgj.byl_process.domain.mypage.service.response.MemberRollReadResponse;
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
    public Boolean registerModifiedPassword(@RequestBody ModifiedPasswordForm modifiedPasswordForm) {
        log.info("passwordCheck(): " + modifiedPasswordForm.getId());
        log.info("passwordCheck(): " + modifiedPasswordForm.getPassword());

        return myPageService.registerModifiedPassword(modifiedPasswordForm);
    }

    @PostMapping("/memberTypeRequest")
    public Boolean memberTypeRequest(@RequestBody MemberTypeRequestDataForm memberTypeRequestDataForm) {
        log.info("아이디(): " + memberTypeRequestDataForm.getMemberId());
        log.info("타입(): " + memberTypeRequestDataForm.getMemberType());
        log.info("메시지(): " + memberTypeRequestDataForm.getMessage());


        return myPageService.registerMemberTypeRequest(memberTypeRequestDataForm);
    }

    @GetMapping("/memberTypeRequestList")
    public List<MemberRollListResponse> memberTypeRequestList() {
        log.info("memberTypeRequestList() 실행");

        return myPageService.requestlist();
    }

    @GetMapping("/memberTypeRequest/{id}")
    public MemberRollReadResponse getMemberTypeRequestDetails(@PathVariable Long id) {
        // 해당 id는 memberTypeRequestId
        log.info("getMemberTypeRequestDetails() 실행 id : " + id);

        return myPageService.readRequest(id);
    }

    @PostMapping("/rollrequestaccept")
    public void rollRequestAccept (@RequestBody MemberTypeRequestCheckForm memberTypeRequestCheckForm) {
        log.info("rollRequestAccept()실행");
        log.info("MemberTypeRequestCheck : " + memberTypeRequestCheckForm);

        myPageService.rollRequestAccept(memberTypeRequestCheckForm);
    }

    @PostMapping("/rollrequestreject")
    public void rollRequestReject (@RequestBody RollRequestRejectForm rollRequestRejectForm) {
        log.info("rollRequestReject()실행");
        log.info("requestId : " + rollRequestRejectForm.getRequestId());

        myPageService.rollRequestReject(rollRequestRejectForm);
    }

    /**
     * Q&A 게시판에서 멤버가 작성한 글 가져오기
     */
    @PostMapping("/findmypost")
    public List<QnaBoardListResponse> findmypost(@RequestBody MemberIdForm memberIdForm) /*@RequestBody = Json으로 변환한걸 받는*/ {
        System.out.println("findmypost() : 왜봐");
        log.info("findmypost() : ");

        System.out.println("Received data: " + memberIdForm.getMemberId());
        return myPageService.findmypost(memberIdForm);
    }
}
