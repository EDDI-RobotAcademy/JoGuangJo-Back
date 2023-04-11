package com.jgj.byl_process.domain.boards.donate.repository;

import com.jgj.byl_process.domain.boards.donate.entity.Donate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface DonateRepository extends JpaRepository<Donate, Long> {

    @Query("SELECT d FROM Donate d JOIN FETCH d.member m WHERE m.id = :memberId")
    List<Donate> findAllByMemberId(@Param("memberId") Long memberId);

}
