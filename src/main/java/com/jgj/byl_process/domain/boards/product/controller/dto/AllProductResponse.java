package com.jgj.byl_process.domain.boards.product.controller.dto;

import com.jgj.byl_process.domain.boards.product.entity.ImageResource;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
public class AllProductResponse {
    final private Long productId;
    final private String productName;
    final private String writer;
    final private Date regDate;


    public AllProductResponse(Long productId, String productName, String writer,
                              Date regDate) {

        this.productId = productId;
        this.productName = productName;
        this.writer = writer;
        this.regDate = regDate;

    }
}