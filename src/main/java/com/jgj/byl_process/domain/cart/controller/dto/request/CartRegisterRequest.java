package com.jgj.byl_process.domain.cart.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartRegisterRequest {

   private Long memberId;
   private Long productId;
   private Long count;

   // count

    public CartRegisterRequest toAddRegisterRequest() {
        return new CartRegisterRequest(memberId, productId, count);
    }
}
