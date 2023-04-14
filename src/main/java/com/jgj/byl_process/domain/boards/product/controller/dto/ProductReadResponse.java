package com.jgj.byl_process.domain.boards.product.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@Getter
@ToString
@AllArgsConstructor
public class ProductReadResponse {

    private Long productId;
    private String productName;
    private String writer;
    private String content;
    private Integer price;
    private Date regDate;

}
