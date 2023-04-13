package com.jgj.byl_process.domain.mypage.service;

import com.jgj.byl_process.domain.member.entity.*;
import com.jgj.byl_process.domain.member.repository.AuthenticationRepository;
import com.jgj.byl_process.domain.member.repository.MemberProfileRepository;
import com.jgj.byl_process.domain.member.repository.MemberRepository;
import com.jgj.byl_process.domain.mypage.MemberToMyPageResponseConverter;
import com.jgj.byl_process.domain.mypage.controller.form.*;
import com.jgj.byl_process.domain.mypage.entity.MemberRoll;
import com.jgj.byl_process.domain.mypage.repository.MemberRollRepository;
import com.jgj.byl_process.domain.mypage.service.response.MemberRollListResponse;
import com.jgj.byl_process.domain.mypage.service.response.MemberRollReadResponse;
import com.jgj.byl_process.domain.mypage.service.response.MyPageResponse;
import com.jgj.byl_process.domain.security.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MyPageServiceImpl implements MyPageService {

    final RedisService redisService;
    final MemberRepository memberRepository;
    final MemberProfileRepository memberProfileRepository;
    final AuthenticationRepository authenticationRepository;
    final MemberRollRepository memberRollRepository;

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

    @Override
    public Boolean register(SaveAddressForm saveAddressForm) {
        Long memberId = saveAddressForm.getMemberId();
        Optional<Member> maybeMember = memberRepository.findById(memberId);

        if (maybeMember.isPresent()) {
            final Member member = maybeMember.get();
            MemberProfile memberProfile = member.getMemberProfile();

            if (memberProfile == null) {
                throw new IllegalStateException("MemberProfile does not exist");
            }

            final Address address = Address.of(saveAddressForm.getCity(),
                                               saveAddressForm.getStreet(),
                                               saveAddressForm.getAddressDetail(),
                                               saveAddressForm.getZipcode().toString());
            memberProfile.setAddress(address);
            memberProfileRepository.save(memberProfile);

            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transactional
    public Boolean passwordCheck(CheckPasswordForm checkPasswordForm) {
        Long memberId = checkPasswordForm.getId();
        Optional<Member> maybeMember = memberRepository.findById(memberId);

        if (maybeMember.isPresent()) {
            Member member = maybeMember.get();
            String password = checkPasswordForm.getPassword();
            return member.isRightPassword(password);
        }
        return false;
    }

    @Override
    @Transactional
    public Boolean registerModifiedPassword(ModifiedPasswordForm modifiedPasswordForm) {
        Long memberId = modifiedPasswordForm.getId();
        Optional<Member> maybeMember = memberRepository.findById(memberId);
        Optional<Authentication> maybeauthentication = authenticationRepository.findById(memberId);

        if(maybeMember.isEmpty() || maybeauthentication.isEmpty()) {
            return false;
        } else {
            final Member member = maybeMember.get();
            final BasicAuthentication authentication = new BasicAuthentication(
                    member,
                    Authentication.BASIC_AUTH,
                    modifiedPasswordForm.getPassword()
            );
            authentication.setAuthenticationId(maybeauthentication.get().getAuthenticationId());
            authenticationRepository.save(authentication);
        }

        return true;
    }

    @Override
    public Boolean registerMemberTypeRequest(MemberTypeRequestDataForm memberTypeRequestDataForm) {
        Long memberId = memberTypeRequestDataForm.getMemberId();
        Optional<Member> maybeMember = memberRepository.findById(memberId);
        // 유효한 아이디인지 확인
        if(maybeMember.isEmpty()) {
            return false;
        } else {
        // 유효한 아이디 이므로 엔티티에 저장 실행
            final Member member = maybeMember.get();
            final String nickname = member.getNickName();
            final String memberType = memberTypeRequestDataForm.getMemberType();
            final String message = memberTypeRequestDataForm.getMessage();
            final MemberRoll memberRoll = new MemberRoll(member, nickname, memberType, message);

            memberRollRepository.save(memberRoll);
        }
        // 새로운 엔티티를 만들어서 저장
        // 그 엔티티의 역할은 뭐냐
        return true;
    }

    @Override
    public List<MemberRollListResponse> requestlist() {
        List<MemberRoll> memberRollList = memberRollRepository.findAll();
        List<MemberRollListResponse> memberRollListResponseList = new ArrayList<>();

        for (MemberRoll memberRoll: memberRollList) {
            memberRollListResponseList.add(new MemberRollListResponse(
                    memberRoll.getMemberRollId(),
                    memberRoll.getNickname(),
                    memberRoll.getMemberType(),
                    memberRoll.getRegDate().toString()
                    )
            );
            System.out.println(memberRoll.getRegDate().toString());
        }

        return memberRollListResponseList;
    }

    @Override
    public MemberRollReadResponse readRequest(Long id) {
        // 들어온 id 가 유효한 id 인지 검색
        Optional<MemberRoll> maybeMemberRoll = memberRollRepository.findById(id);

        if(maybeMemberRoll.isPresent()) {
            // 존재할경우 작업 진행
            // 찾아온 거 가져오기
            MemberRoll memberRoll = maybeMemberRoll.get();
            // 가져온거 매핑하기
            MemberRollReadResponse memberRollReadResponse = new MemberRollReadResponse(
                    memberRoll.getMemberRollId(), memberRoll.getMember().getMemberId(),
                    memberRoll.getNickname(), memberRoll.getMemberType(),
                    memberRoll.getMessage(), memberRoll.getRegDate().toString()
            );
            return memberRollReadResponse;
        } else {
            // 존재하지 않을 경우 작업 중단
            return null;
        }
    }

    @Override
    public void rollRequestAccept(MemberTypeRequestCheckForm memberTypeRequestCheckForm) {
        Optional<MemberRoll> isMemberRoll = memberRollRepository.findById(memberTypeRequestCheckForm.getRequestId());
        Optional<Member> isMember = memberRepository.findById(memberTypeRequestCheckForm.getMemberId());
        if(isMemberRoll.isPresent()) {
            Member member = isMember.get();
            // 하다가 말았는데 이거 개귀찮네
        } else {

        }
    }

    @Override
    public void rollRequestReject (RollRequestRejectForm rollRequestRejectForm) {
        Optional<MemberRoll> isMemberRoll = memberRollRepository.findById(rollRequestRejectForm.getRequestId());

        if(isMemberRoll.isPresent()) {
            memberRollRepository.deleteById(rollRequestRejectForm.getRequestId());
        } else {
            System.out.println("왜 없지?");
        }
    }
    public MyPageResponse getMyPageResponse(Member member) {
        return memberToMyPageResponseConverter.convert(member);
    }
}
