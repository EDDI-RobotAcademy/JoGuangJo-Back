package com.jgj.byl_process.domain.mypage.service;

import com.jgj.byl_process.domain.member.entity.*;
import com.jgj.byl_process.domain.member.repository.AuthenticationRepository;
import com.jgj.byl_process.domain.member.repository.MemberProfileRepository;
import com.jgj.byl_process.domain.member.repository.MemberRepository;
import com.jgj.byl_process.domain.mypage.MemberToMyPageResponseConverter;
import com.jgj.byl_process.domain.mypage.controller.form.CheckPasswordForm;
import com.jgj.byl_process.domain.mypage.controller.form.ModifiedPassword;
import com.jgj.byl_process.domain.mypage.controller.form.SaveAddressForm;
import com.jgj.byl_process.domain.mypage.service.response.MyPageResponse;
import com.jgj.byl_process.domain.security.service.RedisService;
import com.jgj.byl_process.domain.utility.password.PasswordHashConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Boolean registerModifiedPassword(ModifiedPassword modifiedPassword) {
        Long memberId = modifiedPassword.getId();
        Optional<Member> maybeMember = memberRepository.findById(memberId);
        Optional<Authentication> maybeauthentication = authenticationRepository.findById(memberId);

        if(maybeMember.isEmpty() || maybeauthentication.isEmpty()) {
            return false;
        } else {
            final Member member = maybeMember.get();
            final BasicAuthentication authentication = new BasicAuthentication(
                    member,
                    Authentication.BASIC_AUTH,
                    modifiedPassword.getPassword()
            );
            authentication.setId(maybeauthentication.get().getId());
            authenticationRepository.save(authentication);
        }

        return true;
    }

    public MyPageResponse getMyPageResponse(Member member) {
        return memberToMyPageResponseConverter.convert(member);
    }
}
