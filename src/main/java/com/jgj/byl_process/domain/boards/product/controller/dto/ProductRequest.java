package com.jgj.byl_process.domain.boards.product.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.criteria.CriteriaBuilder;

@Getter
@NoArgsConstructor
public class ProductRequest {

    private String productName;
    private String writer;
    private String content;
    private Integer price;
    private Integer quantity;
}
