package com.jgj.byl_process.domain.mypage.service;

import com.jgj.byl_process.domain.member.entity.Member;
import com.jgj.byl_process.domain.member.repository.MemberRepository;
import com.jgj.byl_process.domain.security.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPageServiceImpl implements MyPageService {

    final RedisService redisService;
    final MemberRepository memberRepository;

    @Override
    public String list(String token) {
        System.out.println("Service 실행함!!");

        System.out.println("token : " + token);
//        Long memberId = redisService.getValueByKey(token);
        Long memberId = 2L;
        System.out.println("memberId : " + memberId);

        List<Member> maybeMember = memberRepository.findAllInfoByMembberId(memberId);
        System.out.println("memberRepository 실행함!!");

        String response = "";
        if (!maybeMember.isEmpty()) {
            response = "Member go!!";
        } else {
            response = "No member?";
        }

        List<Member> members = new ArrayList<>();

        for (Member member : members) {
            System.out.println(member);
        }

        return response;
    }
}
