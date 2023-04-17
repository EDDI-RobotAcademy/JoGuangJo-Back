package com.jgj.byl_process.domain.boards.product.controller.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class RequestProductInfo {
    final private String productName;
    final private String writer;
    final private String content;
    final private Integer price;
    final private Integer quantity;
}
