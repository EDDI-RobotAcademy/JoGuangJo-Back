package com.jgj.byl_process.domain.member.repository;

import com.jgj.byl_process.domain.member.entity.Member;
import com.jgj.byl_process.domain.mypage.service.response.MyPageResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {


    // FETCH TYPE LAZY에 의해 proxy 참조가 안되서 join fetch로 강제 참조시킴

    @Query("select m from Member m join fetch m.authentications where m.email = :email")
    Optional<Member> findByEmail(String email);

    @Query("select m from Member m join fetch m.authentications where m.nickName = :nickName")
    Optional<Member> findByNickName(String nickName);

    @Query("SELECT m FROM Member m JOIN FETCH m.memberProfile mp JOIN FETCH m.authentications a WHERE m.memberId = :memberId")
    List<Member> findAllByMemberId(@Param("memberId") Long memberId);

    Optional<Member> findMemberByMemberId(Long memberId);
}