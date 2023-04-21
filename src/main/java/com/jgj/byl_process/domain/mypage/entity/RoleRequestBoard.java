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
public class RoleRequestBoard {

    @Id
    @Column(name = "roll_request_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rolerequestId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Getter
    @Column(length = 32, nullable = false)
    private String nickname;

    @Getter
    @Column(nullable = false)
    private String memberType;

    @Lob
    private String message;

    @Getter
    @CreationTimestamp
    private Date regDate;

    public RoleRequestBoard(Member member, String nickname, String memberType, String message) {
        this.member = member;
        this.nickname = nickname;
        this.memberType = memberType;
        this.message = message;
    }
}
