package com.jgj.byl_process.domain.order.controller.dto;

import com.jgj.byl_process.domain.boards.product.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderInfo {

    private String name;
    private String address;
    private String addressDetail;
    private String phone;
    private Long productId;
    private Product product;

}
