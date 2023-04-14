package com.jgj.byl_process.domain.cart.repository;

import com.jgj.byl_process.domain.cart.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    @Query("select ci from CartItem ci join fetch ci.product p join fetch ci.cart c where c.member.memberId = :memberId")
    List<CartItem> findCartItemListWithMemberId(Long memberId);

    Optional<CartItem> findByCartItemIdAndCart_CartId(Long cartItemId, Long cartId);
}
