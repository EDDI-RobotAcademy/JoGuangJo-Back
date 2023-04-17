package com.jgj.byl_process.domain.boards.product.repository;

import com.jgj.byl_process.domain.boards.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p left join fetch p.imageResourceList irl where p.productId = :id")
    Optional<Product> findImagePathById(Long id);


    Optional<Product> findByProductId(Long productId);


}
