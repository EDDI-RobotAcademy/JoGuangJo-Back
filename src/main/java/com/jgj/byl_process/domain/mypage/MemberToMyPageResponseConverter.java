package com.jgj.byl_process.domain.mypage;

import com.jgj.byl_process.domain.member.entity.Authentication;
import com.jgj.byl_process.domain.member.entity.Member;
import com.jgj.byl_process.domain.mypage.service.response.MyPageResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MemberToMyPageResponseConverter implements Converter<Member, MyPageResponse> {

    @Override
    public MyPageResponse convert(Member member) {
        MyPageResponse myPageResponse = new MyPageResponse();

        myPageResponse.setId(member.getMemberId());
        myPageResponse.setEmail(member.getEmail());
        myPageResponse.setCity(member.getMemberProfile().getAddress().getCity());
        myPageResponse.setStreet(member.getMemberProfile().getAddress().getStreet());
        myPageResponse.setAddressDetail(member.getMemberProfile().getAddress().getAddressDetail());
        myPageResponse.setZipcode(member.getMemberProfile().getAddress().getZipcode());

        Authentication auth = member.getAuthentications().iterator().next();
        myPageResponse.setAuthenticationType(auth.getAuthenticationType());

        return myPageResponse;
    }
}