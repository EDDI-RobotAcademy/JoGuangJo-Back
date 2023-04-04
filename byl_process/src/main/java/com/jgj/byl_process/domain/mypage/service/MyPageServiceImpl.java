package com.jgj.byl_process.domain.mypage.service;

import com.jgj.byl_process.domain.member.entity.Member;
import com.jgj.byl_process.domain.member.repository.MemberRepository;
import com.jgj.byl_process.domain.mypage.MemberToMyPageResponseConverter;
import com.jgj.byl_process.domain.mypage.service.response.MyPageResponse;
import com.jgj.byl_process.domain.security.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MyPageServiceImpl implements MyPageService {

    final RedisService redisService;
    final MemberRepository memberRepository;
    @Autowired
    private MemberToMyPageResponseConverter memberToMyPageResponseConverter;

    @Override
    public List<MyPageResponse> list(String token) {
        Long memberId = redisService.getValueByKey(token);
        System.out.println("memberId : "+memberId);

        List<Member> members = memberRepository.findAllByMemberId(memberId);

        List<MyPageResponse> resultList = members.stream()
                .map(this::getMyPageResponse)
                .collect(Collectors.toList());

        for (MyPageResponse response : resultList) {
            System.out.println(response.toString());
        }

        return resultList;
    }

    public MyPageResponse getMyPageResponse(Member member) {
        return memberToMyPageResponseConverter.convert(member);
    }
}
