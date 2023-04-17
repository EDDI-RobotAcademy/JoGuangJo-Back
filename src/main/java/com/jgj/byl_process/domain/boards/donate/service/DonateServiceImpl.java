package com.jgj.byl_process.domain.boards.donate.service;

import com.jgj.byl_process.domain.boards.donate.service.request.DonateModifyRequest;
import com.jgj.byl_process.domain.boards.donate.service.request.DonateRegisterRequest;
import com.jgj.byl_process.domain.boards.donate.service.response.DonateListResponse;
import com.jgj.byl_process.domain.boards.donate.service.response.DonateReadResponse;
import com.jgj.byl_process.domain.boards.donate.repository.DonateRepository;
import com.jgj.byl_process.domain.member.repository.MemberRepository;
import com.jgj.byl_process.domain.boards.donate.entity.Donate;
import com.jgj.byl_process.domain.member.entity.Member;

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


    // 책 기부 페이지에서 기부 신청하는 메서드
    @Override
    public Boolean register(DonateRegisterRequest donateRegisterRequest) {

        Optional<Member> maybeMember = memberRepository.findById(donateRegisterRequest.getMemberId());

        if(maybeMember.isEmpty()) {
            System.out.println(
                "해당 회원이 존재하지 않습니다. member 테이블 조회에 쓰인 memberId: " + donateRegisterRequest.getMemberId()
            );
            return false;
        } else {
            final Donate donate = donateRegisterRequest.toDonate(maybeMember.get());
            Donate savedDonate = donateRepository.save(donate);
            System.out.println("donate 테이블에 해당 회원의 기부 데이터를 저장했습니다: " + savedDonate);
            return true;
        }
    }
    

    // 마이페이지에서 자기 기부내역 목록조회하는 메서드
    @Override
    public List<DonateListResponse> list(Long memberId) {

        List<Donate> donateList = donateRepository.findAllByMemberId(memberId);
        System.out.println("donate 테이블 조회에 쓰인 memberId : " + memberId);

        List<DonateListResponse> donateListResponse_List = new ArrayList<>();

        for (Donate donate: donateList) {
            donateListResponse_List.add(new DonateListResponse(
                donate.getDonateId(), donate.getRegDate(), donate.getUpdDate()
            ));
        }
        System.out.println("해당 memberId를 가진 donate 테이블의 데이터를 프론트로 반환했습니다");

        return donateListResponse_List;
    }


    // 마이페이지에서 자기 기부내역 상세조회하는 메서드
    @Override
    public DonateReadResponse read(Long donateId) {
        Optional<Donate> maybeDonate = donateRepository.findById(donateId);
        System.out.println("donate 테이블 조회에 쓰인 donateId : " + donateId);

        if (maybeDonate.isEmpty()) {
            System.out.println("해당 donateId가 donate 테이블에 없습니다.");
            return null;
        } else {
            Donate donate = maybeDonate.get();
            DonateReadResponse donateReadResponse = DonateReadResponse.fromDonate(donate);

            System.out.println("해당 donateId를 가진 donate 데이터를 프론트로 반환했습니다");
            return donateReadResponse;
        }
    }


    // 마이페이지에서 자기 방문수거 기부내역 수정하는 메서드
    @Override
    public Boolean modify(DonateModifyRequest donateModifyRequest) {

        Optional<Donate> maybeDonate = donateRepository.findById(donateModifyRequest.getDonateId());

        System.out.println(
            "modifyRequest 의 donateId, name : " + donateModifyRequest.getDonateId() + donateModifyRequest.getName()
        );

            if (maybeDonate.isEmpty()) {
            System.out.println("donateId: " + donateModifyRequest.getDonateId() + "에 해당하는 donate 데이터가 존재하지 않습니다.");
            return false;

        } else {
            Donate donate = maybeDonate.get();

            donate.updateFromRequest(donateModifyRequest);

            donateRepository.save(donate);

            System.out.println("해당 donateId 의 데이터를 수정했습니다" + donate);
            return true;
        }
    }




    // 마이페이지에서 자기 방문수거 기부내역 삭제하는 메서드
    @Override
    public Boolean delete(Long donateId) {

        Optional<Donate> maybeDonate = donateRepository.findById(donateId);

        if (maybeDonate.isEmpty()) {
            System.out.println("해당 donateId의 데이터가 존재하지 않습니다. donate 테이블 조회에 쓰인 donateId: " + donateId);
            return false;

        } else {
            donateRepository.delete(maybeDonate.get());
            return true;
        }
    }

}
