package com.jgj.byl_process.domain.member.entity;

import com.jgj.byl_process.domain.boards.donate.entity.Donate;
import com.jgj.byl_process.domain.cart.entity.Cart;
import com.jgj.byl_process.domain.mypage.entity.RoleRequestBoard;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Entity
@NoArgsConstructor
public class Member {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Getter
    @Column(nullable = false)
    private String email;

    @Getter
    @Column(nullable = false)
    private String nickName;

    @OneToOne(mappedBy = "member", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private MemberProfile memberProfile;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Authentication> authentications = new HashSet<>();

    @OneToOne(mappedBy = "member", fetch = FetchType.LAZY)
    private Cart cart;

    /* (박지영) Member 테이블과 Donate 테이블 간의 관계 설정
       한 member 는 여러번의 donate 를 할 수 있다
       member 가 탈퇴하면 그 member 의 모든 donate 내역이 사라진다
     */
    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Donate> donates;

    @OneToOne(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private RoleRequestBoard rollRequestBoard;

    @Getter
    @OneToOne(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Role role;

    public Member(String email, String nickName, MemberProfile memberProfile) {
        this.email = email;
        this.nickName = nickName;
        this.memberProfile = memberProfile;
        memberProfile.setMember(this);
    }

    public Member(String email, String nickName) {
        this.email = email;
        this.nickName = nickName;
    }

    public boolean isRightPassword(String plainToCheck) {
        final Optional<Authentication> maybeBasicAuth = findBasicAuthentication();

        if (maybeBasicAuth.isPresent()) {
            final BasicAuthentication authentication = (BasicAuthentication) maybeBasicAuth.get();
            return authentication.isRightPassword(plainToCheck);
        }

        return false;
    }

    private Optional<Authentication> findBasicAuthentication () {
        return authentications
                .stream()
                .filter(authentication -> authentication instanceof BasicAuthentication)
                .findFirst();
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + memberId +
                ", email='" + email + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }

    public MemberProfile getMemberProfile() {
        return this.memberProfile;
    }

    public Set<Authentication> getAuthentications() {
        return this.authentications;
    }


}