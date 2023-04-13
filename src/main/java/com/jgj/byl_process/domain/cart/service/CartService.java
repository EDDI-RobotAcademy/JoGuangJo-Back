package com.jgj.byl_process.domain.cart.service;

import com.jgj.byl_process.domain.cart.controller.dto.request.CartListRequest;
import com.jgj.byl_process.domain.cart.controller.dto.request.CartRegisterRequest;
import com.jgj.byl_process.domain.cart.controller.dto.response.CartItemListResponse;

import java.util.List;

public interface CartService {


    List<CartItemListResponse> cartList(Long memberId);

}
