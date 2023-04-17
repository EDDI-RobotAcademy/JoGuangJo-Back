package com.jgj.byl_process.domain.cart.controller;


//import com.jgj.byl_process.domain.cart.controller.dto.request.CartListRequest;
//import com.jgj.byl_process.domain.cart.controller.dto.request.CartRegisterRequest;

import com.jgj.byl_process.domain.cart.controller.dto.request.CartRegisterRequest;

import com.jgj.byl_process.domain.cart.controller.dto.response.CartItemListResponse;
import com.jgj.byl_process.domain.cart.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/cart")
public class CartController {

    final private CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/register")
    public void cartRegister(@RequestBody CartRegisterRequest cartRegisterRequest){
        log.info("회원 카트에 아이템 추가" + cartRegisterRequest.toString());
        cartService.register(cartRegisterRequest.toAddRegisterRequest());
    }

    @GetMapping("/list/{memberId}")
    public List<CartItemListResponse> cartItemListResponseList(@PathVariable("memberId") Long memberId){
        List<CartItemListResponse> cartItemListResponseList = cartService.cartList(memberId);

        System.out.println("after returnCartItemList(): " + cartItemListResponseList);

        return cartItemListResponseList;
    }

    @DeleteMapping("/delete")
    public void cartItemRemove(@RequestParam("cartItemIds") List<Long> cartItemIds){
        log.info("\n" + "cartItemRemove() 동작 : " + cartItemIds);

        cartService.remove(cartItemIds);
    }
}
