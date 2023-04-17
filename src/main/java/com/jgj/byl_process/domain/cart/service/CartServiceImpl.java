package com.jgj.byl_process.domain.cart.service;

import com.jgj.byl_process.domain.boards.product.entity.Product;
import com.jgj.byl_process.domain.boards.product.repository.ImageResourceRepository;
import com.jgj.byl_process.domain.boards.product.repository.ProductRepository;
//import com.jgj.byl_process.domain.cart.controller.dto.request.CartRegisterRequest;
import com.jgj.byl_process.domain.cart.controller.dto.request.CartRegisterRequest;
import com.jgj.byl_process.domain.cart.controller.dto.response.CartItemListResponse;
import com.jgj.byl_process.domain.cart.entity.Cart;
import com.jgj.byl_process.domain.cart.entity.CartItem;
import com.jgj.byl_process.domain.cart.repository.CartItemRepository;
import com.jgj.byl_process.domain.cart.repository.CartRepository;
//import com.jgj.byl_process.domain.member.entity.Member;
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
    public void register(CartRegisterRequest cartRegisterRequest) {
        Long memberId = cartRegisterRequest.getMemberId();
        Long productId = cartRegisterRequest.getProductId();
        Long count = cartRegisterRequest.getCount();

        // 카트가 없으면 카트를 생성하고 카트가 있으면 아이템 추가
        Cart cart = createCartIfNoCart(memberId);

        Optional<Product> maybeProduct = productRepository.findByProductId(productId);
        Product product = null;

        if(maybeProduct.isPresent()) {
            product = maybeProduct.get();

            // 현재 멤버의 모든 장바구니 아이템 목록을 조회
            List<CartItem> cartItemList = cartItemRepository.findCartItemListWithMemberId(memberId);
            Long cartItemId = null;

            for (CartItem cartItem : cartItemList) {
                if (cartItem.getProduct().getProductId().equals(productId)) {
                    cartItemId = cartItem.getCartItemId();
                    break;
                }
            }

            // 중복 상품 확인
            Optional<CartItem> maybeCartItem = cartItemRepository.findByCartItemIdAndCart_CartId(cartItemId, cart.getCartId());

            if(maybeCartItem.isPresent()) {
                // 중복 상품 존재시 수량 증가
                CartItem cartItem = maybeCartItem.get();
                cartItem.setCount(cartItem.getCount() + count);
                cartItemRepository.save(cartItem);
            } else {
                // 중복 상품 없을시 상품 추가
                CartItem cartItem = new CartItem(product, count);
                cartItem.setCart(cart);
                cartItemRepository.save(cartItem);
            }
        }
    }

    private Cart createCartIfNoCart(Long memberId) {
        Cart cart = null;
        Optional<Cart> maybeCart = cartRepository.findById(memberId);

        if(maybeCart.isEmpty()) {
            Optional<Member> maybeMember = memberRepository.findMemberByMemberId(memberId);
                Member member = new Member();

                if (maybeMember.isPresent()) {
                    member = maybeMember.get();
                }

                System.out.println(member);

                cart = new Cart(1L, member);
                cartRepository.save(cart);
            }


        if(maybeCart.isPresent()){
            cart = maybeCart.get();
        }

        return cart;
    }

@Override
public List<CartItemListResponse> cartList(Long memberId){
    List<CartItem> cartItemList = cartItemRepository.findCartItemListWithMemberId(memberId);

    List<CartItemListResponse> cartItemListResponseList = new ArrayList<>();

        for (CartItem cartItem : cartItemList) {

            Long cartId = cartItem.getCart().getCartId();
            Optional<Cart> cart = cartRepository.findById(cartId);
            Long totalCount = cart.isPresent() ? cart.get().getTotalCount() : 0L;

            Long productId = cartItem.getProduct().getProductId();
            Optional<Product> product = productRepository.findByProductId(productId);
            Long price = product.isPresent() ? product.get().getPrice() : 0L;


//        Optional<ImageResource> imageResource = Optional.ofNullable(imageResourceRepository.findByProduct_ProductId(productId));
//        String thumbnail = imageResource.isPresent() ? imageResource.get().getThumbnail() : "null";
            // 썸네일 상품에서 완료되면 추가예정

            CartItemListResponse cartItemListResponse = new CartItemListResponse(cartItem, totalCount, price, memberId);
            cartItemListResponseList.add(cartItemListResponse);
        }

    return cartItemListResponseList;
}
@Override
public void remove(List<Long> cartItemIds){
        cartItemRepository.deleteAllById(cartItemIds);
}
}
