package com.jgj.byl_process.domain.member.entity;

import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rollId;

    @Setter
    @Column(nullable = false)
    private String memberType;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Role(Member member, String memberType) {
        this.member = member;
        this.memberType = memberType;
    }
}
