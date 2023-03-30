package com.jgj.byl_process.domain.member.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@NoArgsConstructor
public class Member {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    public Member(String email, String nickName,MemberProfile memberProfile) {
        this.email = email;
        this.nickName = nickName;
        this.memberProfile = memberProfile;
        memberProfile.setMember(this);
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
}