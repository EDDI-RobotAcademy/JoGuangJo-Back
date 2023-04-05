package com.jgj.byl_process.domain.boards.product.repository;

import com.jgj.byl_process.domain.boards.product.entity.ImageResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImageResourceRepository extends JpaRepository<ImageResource, Long> {

    @Query("select ir from ImageResource ir join ir.product p where p.productId = :id")
    List<ImageResource> findImagePathByProductId(Long id);
}
