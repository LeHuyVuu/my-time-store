package com.boboibo.mytimestore.repository;

import com.boboibo.mytimestore.model.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
CartItem findByProduct_ProductIdAndUser_UserId(Long productId, Long userId);
}

