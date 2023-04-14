package com.jgj.byl_process.domain.boards.product.repository;

import com.jgj.byl_process.domain.boards.product.entity.ImageResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImageResourceRepository extends JpaRepository<ImageResource, Long> {

    @Query("select ir from ImageResource ir join ir.product p where p.productId = :id")
    List<ImageResource> findImagePathByProductId(Long id);

//    @Query("Select ir from ImageResource ir join fetch ir.product p where p.productId = :productId")
//    ImageResource findByProduct_ProductId(Long productId);
    // 상품페이지 썸네일 완료되면 사용 예정
}
