package com.jgj.byl_process.domain.boards.donate.service.response;

import com.jgj.byl_process.domain.boards.donate.controller.dto.request.DonateVisitRegisterRequest;
import com.jgj.byl_process.domain.boards.donate.entity.Donate;
import com.jgj.byl_process.domain.boards.donate.repository.DonateRepository;
import com.jgj.byl_process.domain.member.entity.Member;
import com.jgj.byl_process.domain.member.repository.MemberRepository;
import com.jgj.byl_process.domain.security.service.RedisService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DonateServiceImpl implements DonateService {

    final private MemberRepository memberRepository;
    final private DonateRepository donateRepository;
    final private RedisService redisService;


    @Override
    public Boolean donateVisitRegister(DonateVisitRegisterRequest donateVisitRegisterRequest) {

        Optional<Member> maybeMember = memberRepository.findByEmail(donateVisitRegisterRequest.getEmail());

        System.out.println("조회에 쓰인 이메일: " + donateVisitRegisterRequest.getEmail());
        System.out.println("조회 결과 해당 이메일을 가진 회원 존재 여부: " + maybeMember.isPresent());

        if (maybeMember.isPresent()) {
            Member member = maybeMember.get();
            final Donate donate = donateVisitRegisterRequest.toDonate(member);
            Donate savedDonate = donateRepository.save(donate);
            System.out.println("donate 테이블에 해당 회원의 방문기부 데이터를 저장했습니다: " + savedDonate);
            return true;
        } else {
            System.out.println("donate 테이블에 해당 회원의 방문기부 데이터를 저장하지 못했습니다");
            return false;
        }
    }
}
