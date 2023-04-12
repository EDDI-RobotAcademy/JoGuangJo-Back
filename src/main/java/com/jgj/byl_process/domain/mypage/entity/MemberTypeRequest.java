package com.jgj.byl_process.domain.mypage.entity;

import com.jgj.byl_process.domain.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class MemberTypeRequest {

    @Id
    @Column(name = "membertyperequest_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberTypeRequestId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(length = 32, nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String memberType;

    @Lob
    private String message;

    @CreationTimestamp
    private Date regDate;

    public MemberTypeRequest(Member member, String nickname, String memberType, String message) {
        this.member = member;
        this.nickname = nickname;
        this.memberType = memberType;
        this.message = message;
    }
}
