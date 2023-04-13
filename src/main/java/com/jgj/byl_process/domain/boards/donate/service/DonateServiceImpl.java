package com.jgj.byl_process.domain.boards.donate.service;

import com.jgj.byl_process.domain.boards.donate.controller.dto.request.DonateVisitRequest;
import com.jgj.byl_process.domain.boards.donate.controller.dto.response.MyDonateListResponse;
import com.jgj.byl_process.domain.boards.donate.controller.dto.response.MyDonateReadResponse;
import com.jgj.byl_process.domain.boards.donate.repository.DonateRepository;
import com.jgj.byl_process.domain.member.repository.MemberRepository;
import com.jgj.byl_process.domain.boards.donate.entity.Donate;
import com.jgj.byl_process.domain.member.entity.Member;
import com.jgj.byl_process.domain.security.service.RedisService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class DonateServiceImpl implements DonateService {

    final private MemberRepository memberRepository;
    final private DonateRepository donateRepository;
    final private RedisService redisService;


    // 방문수거 페이지에서 방문수거 기부신청하는 메서드
    @Override
    public Boolean register(DonateVisitRequest donateVisitRequest) {

        Optional<Member> maybeMember = memberRepository.findByMemberId(donateVisitRequest.getMemberId());

        System.out.println("조회에 쓰인 memberId: " + donateVisitRequest.getMemberId());
        System.out.println("조회 결과 해당 memberId를 가진 회원 존재 여부: " + maybeMember.isPresent());

        if (maybeMember.isPresent()) {
            Member member = maybeMember.get();
            final Donate donate = donateVisitRequest.toDonate(member);
            Donate savedDonate = donateRepository.save(donate);
            System.out.println("donate 테이블에 해당 회원의 방문기부 데이터를 저장했습니다: " + savedDonate);
            return true;
        } else {
            System.out.println("donate 테이블에 해당 회원의 방문기부 데이터를 저장하지 못했습니다");
            return false;
        }
    }
    

    // 마이페이지에서 자기 방문수거 기부내역 목록조회하는 메서드
    @Override
    public List<MyDonateListResponse> list(Long memberId) {

        List<Donate> MyDonateList = donateRepository.findAllByMemberId(memberId);
        System.out.println("donate 테이블 조회에 쓰인 memberId : " + memberId);

        List<MyDonateListResponse> ResponseList = new ArrayList<>();

        for (Donate donate: MyDonateList) {
            ResponseList.add(new MyDonateListResponse(
                    donate.getMemberId(), donate.getDonateVisitId(), donate.getRegDate(), donate.getUpdDate()
            ));
        }
        System.out.println("해당 memberId를 가진 donate 데이터를 프론트로 반환했습니다");

        return ResponseList;
    }


    // 마이페이지에서 자기 방문수거 기부내역 상세조회하는 메서드
    @Override
    public MyDonateReadResponse read(Long donateVisitId) {
        Optional<Donate> maybeDonate = donateRepository.findById(donateVisitId);
        System.out.println("donate 테이블 조회에 쓰인 donateVisitId : " + donateVisitId);

        if (maybeDonate.isEmpty()) {
            System.out.println("해당 donateVisitId가 donate 테이블에 없습니다.");
            return null;
        }

        Donate donate = maybeDonate.get();

        MyDonateReadResponse ReadResponse = new MyDonateReadResponse(
                donate.getMemberId(), donate.getDonateVisitId(),
                donate.getName(), donate.getEmail(), donate.getPhone(),
                donate.getQuantity(), donate.getQuality(),
                donate.getVisitDate(), donate.getVisitTime(),
                donate.getZipcode(), donate.getCity(), donate.getStreet(), donate.getAddressDetail(),
                donate.getRegDate(), donate.getUpdDate()
        );

        System.out.println("해당 donateVisitId를 가진 donate 데이터를 프론트로 반환했습니다");
        return ReadResponse;
    }

}
