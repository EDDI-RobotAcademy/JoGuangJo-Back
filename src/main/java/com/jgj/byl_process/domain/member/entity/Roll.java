package com.jgj.byl_process.domain.member.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class Roll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rollId;

    @Column(nullable = false)
    private String memberType;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Roll (Member member, String memberType) {
        this.member = member;
        this.memberType = memberType;
    }
}
