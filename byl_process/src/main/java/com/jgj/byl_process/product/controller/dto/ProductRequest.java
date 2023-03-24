package com.jgj.byl_process.product.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductRequest {

    private String productName;
    private String writer;
    private String content;
    private Integer price;
}
