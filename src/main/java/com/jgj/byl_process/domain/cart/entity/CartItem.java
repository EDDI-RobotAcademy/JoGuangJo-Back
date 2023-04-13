package com.jgj.byl_process.domain.cart.entity;

import com.jgj.byl_process.domain.boards.product.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@ToString(exclude = "cart")
@Getter
@Setter
@NoArgsConstructor
public class CartItem {

    @Id
    @Column(length = 16)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(length = 16)
    private Long count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    public CartItem(Product product, Long count) {
        this.product = product;
        this. count = count;
    }

    public void setCart (Cart cart) {
        this.cart = cart;
    }

}
