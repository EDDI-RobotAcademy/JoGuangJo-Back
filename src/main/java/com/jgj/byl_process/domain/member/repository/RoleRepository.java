package com.jgj.byl_process.domain.member.repository;

import com.jgj.byl_process.domain.member.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleRepository extends JpaRepository <Role, Long> {

    @Query("select r from Role r join r.member m where m.id = :memberId")
    Optional<Role> findByMemberId(Long memberId);
}
