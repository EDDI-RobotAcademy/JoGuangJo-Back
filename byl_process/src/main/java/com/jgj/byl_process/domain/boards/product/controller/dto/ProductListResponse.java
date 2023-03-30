package com.jgj.byl_process.domain.boards.product.controller.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Getter
@RequiredArgsConstructor
public class ProductListResponse {
    final private Long productId;
    final private String productName;
    final private String writer;
    final private Date regDate;
}
