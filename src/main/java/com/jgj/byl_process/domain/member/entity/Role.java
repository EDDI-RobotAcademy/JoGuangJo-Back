package com.jgj.byl_process.domain.member.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Setter
    @Getter
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
