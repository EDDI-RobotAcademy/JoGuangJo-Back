package com.jgj.byl_process.domain.cart.service;

import com.jgj.byl_process.domain.boards.product.entity.Product;
import com.jgj.byl_process.domain.boards.product.repository.ImageResourceRepository;
import com.jgj.byl_process.domain.boards.product.repository.ProductRepository;
import com.jgj.byl_process.domain.cart.controller.dto.request.CartRegisterRequest;
import com.jgj.byl_process.domain.cart.controller.dto.response.CartItemListResponse;
import com.jgj.byl_process.domain.cart.entity.Cart;
import com.jgj.byl_process.domain.cart.entity.CartItem;
import com.jgj.byl_process.domain.cart.repository.CartItemRepository;
import com.jgj.byl_process.domain.cart.repository.CartRepository;
import com.jgj.byl_process.domain.member.entity.Member;
import com.jgj.byl_process.domain.member.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ProductRepository productRepository;
    private ImageResourceRepository imageResourceRepository;


@Override
public List<CartItemListResponse> cartList(Long memberId){
    List<CartItem> cartItemList = cartItemRepository.findCartItemListByMemberId(memberId);

    List<CartItemListResponse> cartItemListResponseList = new ArrayList<>();

    for(CartItem cartItem: cartItemList) {
        Long cartId = cartItem.getCart().getCartId();
        Optional<Cart> cart = cartRepository.findById(cartId);
        Long totalCount = cart.isPresent() ? cart.get().getTotalCount() : 0L;

        Long productId = cartItem.getProduct().getProductId();
        Optional<Product> product = productRepository.findById(productId);
        Long price = product.isPresent() ? product.get().getPrice() : 0L;

//        Optional<ImageResource> imageResource = Optional.ofNullable(imageResourceRepository.findByProduct_ProductId(productId));
//        String thumbnail = imageResource.isPresent() ? imageResource.get().getThumbnail() : "null";

        CartItemListResponse cartItemListResponse = new CartItemListResponse(cartItem, totalCount, price, memberId);
        cartItemListResponseList.add(cartItemListResponse);
    }

    System.out.println("cartItemListResponseList: " + cartItemListResponseList);

    return cartItemListResponseList;
}
}
