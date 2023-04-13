package com.jgj.byl_process.domain.cart.repository;

import com.jgj.byl_process.domain.cart.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    @Query("select ci from CartItem ci join fetch ci.product p join fetch ci.cart c where c.member.id = :memberId")
    List<CartItem> findCartItemListByMemberId(Long memberId);
}
