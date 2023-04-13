package com.jgj.byl_process.domain.cart.controller.dto.response;

import com.jgj.byl_process.domain.boards.product.entity.Product;
import com.jgj.byl_process.domain.cart.entity.CartItem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class CartItemListResponse {
    private Long cartItemId;

    private Long memberId;

    private Product product;

    private Long count;
    // 상품 개수 (ex. A상품 2개)

    private Long totalCount;
    // from cart

    private Long price;
    // from product

//    private String thumbnail;
//  from ImageResource
    // product 이미지 관련 완성 되면 썸네일 추가할 계획

//    public CartItemListResponse(CartItem cartItem, Long totalCount, Long price, String thumbnail, Long memberId) {
//        this.cartItemId = cartItem.getCartItemId();
//        this.product = cartItem.getProduct();
//        this.count = cartItem.getCount();
//        this.totalCount = totalCount;
//        this.price = price;
//        this.memberId = memberId;
//    }
    // product 이미지 관련 완성 되면 썸네일 추가할 계획

    public CartItemListResponse(CartItem cartItem, Long totalCount, Long price, Long memberId) {
        this.cartItemId = cartItem.getCartItemId();
        this.product = cartItem.getProduct();
        this.count = cartItem.getCount();
        this.totalCount = totalCount;
        this.price = price;
        this.memberId = memberId;
    }

}
