package com.jgj.byl_process.domain.boards.product.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Product {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(length = 128, nullable = false)
    private String productName;

    @Column(length = 32, nullable = false)
    private String writer;

    @Lob
    private String content;

    @JsonManagedReference
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ImageResource> imageResourceList = new ArrayList<>();

    private Integer price;

    // New field for product quantity
    @Column(nullable = false)
    private Integer quantity;

    @CreationTimestamp
    private Date regDate;

    @UpdateTimestamp
    private Date updDate;

    public void setImageResource(ImageResource imageResource) {
        imageResource.setProduct(this);
        this.imageResourceList.add(imageResource);
    }

    public void setImageResourceList(List<ImageResource> imageResourceList) {
        for (ImageResource imageResource : imageResourceList) {
            imageResource.setProduct(this);
        }
        this.imageResourceList.clear();
        this.imageResourceList.addAll(imageResourceList);
    }

}
