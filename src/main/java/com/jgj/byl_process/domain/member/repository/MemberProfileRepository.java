package com.jgj.byl_process.domain.member.repository;

import com.jgj.byl_process.domain.member.entity.Authentication;
import com.jgj.byl_process.domain.member.entity.MemberProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberProfileRepository extends JpaRepository<MemberProfile, Long> {
}
