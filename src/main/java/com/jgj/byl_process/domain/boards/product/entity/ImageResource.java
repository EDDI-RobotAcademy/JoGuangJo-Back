package com.jgj.byl_process.domain.boards.product.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class ImageResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageResourcePath;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

//    @Column(length = 32, nullable = false)
//    private String thumbnail;
    // 상품페이지 썸네일 완료되면 사용 예정

    public ImageResource(String imageResourcePath) {
        this.imageResourcePath = imageResourcePath;
    }

    public void setFilePath(String imageResourcePath) {
        this.imageResourcePath = imageResourcePath;
    }
}
