package com.jgj.byl_process.domain.cart.repository;

import com.jgj.byl_process.domain.boards.qna.entity.QnaBoardImgResource;
import com.jgj.byl_process.domain.cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
