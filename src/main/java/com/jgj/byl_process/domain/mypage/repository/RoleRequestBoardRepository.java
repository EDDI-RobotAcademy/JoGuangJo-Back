package com.jgj.byl_process.domain.mypage.repository;

import com.jgj.byl_process.domain.mypage.entity.RoleRequestBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleRequestBoardRepository extends JpaRepository<RoleRequestBoard, Long> {


    @Query("select r from RoleRequestBoard r where r.member.memberId = :memberId")
    Optional<RoleRequestBoard> findByMemberId(Long memberId);
}
