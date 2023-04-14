package com.jgj.byl_process.domain.cart.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jgj.byl_process.domain.boards.product.entity.Product;
import com.jgj.byl_process.domain.member.entity.Member;
import lombok.*;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString(exclude = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @Column(name = "total_count")
    private Long totalCount;

    @OneToMany(mappedBy = "cart", fetch = FetchType.LAZY)
    private List<CartItem> cartItemList = new ArrayList<>();


    public Cart(long totalCount, Member member) {
        this.totalCount = totalCount;
        this.member = member;
    }
}
