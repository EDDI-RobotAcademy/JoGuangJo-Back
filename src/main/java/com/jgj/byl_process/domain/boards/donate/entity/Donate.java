package com.jgj.byl_process.domain.boards.donate.entity;

import com.jgj.byl_process.domain.boards.donate.service.request.DonateModifyRequest;
import com.jgj.byl_process.domain.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Donate {

    /* Member 테이블과 Donate 테이블 간의 관계 설정
    한 member 는 여러번의 donate 를 할 수 있다. member 가 탈퇴하면 그 member 의 모든 donate 내역이 사라진다. */
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "donateId")
    private Long donateId;

    @Getter
    @Column(name = "donorName", length = 64)
    private String name;

    @Getter
    @Column(name = "donorEmail", length = 128)
    private String email;

    @Getter
    @Column(name = "donorPhone", length = 64)
    private String phone;

    @Getter
    @Column(name = "quantity", length = 32)
    private String quantity;

    @Getter
    @Column(name = "quality", length = 32)
    private String quality;

    @Getter
    @Column(name = "visitDate", length = 64)
    private String visitDate;

    @Getter
    @Column(name = "visitTime", length = 64)
    private String visitTime;

    @Getter
    @Column(name = "zipcode", length = 64)
    private String zipcode;

    @Getter
    @Column(name = "city", length = 128)
    private String city;

    @Getter
    @Column(name = "street", length = 128)
    private String street;

    @Getter
    @Column(name = "addressDetail", length = 128)
    private String addressDetail;

    @Getter
    @CreationTimestamp
    private Date regDate;

    @Getter
    @UpdateTimestamp
    private Date updDate;


    public Donate(Member member, String name, String email, String phone, String quantity,
                  String quality, String visitDate, String visitTime, String zipcode,
                  String city, String street, String addressDetail) {
        this.member = member;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.quantity = quantity;
        this.quality = quality;
        this.visitDate = visitDate;
        this.visitTime = visitTime;
        this.zipcode = zipcode;
        this.city = city;
        this.street = street;
        this.addressDetail = addressDetail;
    }

    public void updateFromRequest(DonateModifyRequest request) {
        this.name = request.getName();
        this.email = request.getEmail();
        this.phone = request.getPhone();
        this.quantity = request.getQuantity();
        this.quality = request.getQuality();
        this.visitDate = request.getVisitDate();
        this.visitTime = request.getVisitTime();
        this.zipcode = request.getZipcode();
        this.city = request.getCity();
        this.street = request.getStreet();
        this.addressDetail = request.getAddressDetail();
    }


    public Long getMemberId() {
        return member.getMemberId();
    }

    public Long getDonateId() {
        return donateId;
    }


}

