package com.jgj.byl_process.domain.boards.donate.repository;

import com.jgj.byl_process.domain.boards.donate.entity.Donate;
import com.jgj.byl_process.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface DonateRepository extends JpaRepository<Donate, Long> {

    @Query("SELECT d FROM Donate d JOIN FETCH d.member m WHERE m.memberId = :memberId")
    List<Donate> findAllByMemberId(@Param("memberId") Long memberId);

    @Query("SELECT d.member FROM Donate d WHERE d.donateId = ?1")
    Optional<Member> findMemberByDonateId(Long donateId);

}

