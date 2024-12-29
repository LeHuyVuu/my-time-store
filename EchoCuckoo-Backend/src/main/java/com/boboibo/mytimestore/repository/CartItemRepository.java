package com.boboibo.mytimestore.repository;

import com.boboibo.mytimestore.model.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
CartItem findByProduct_ProductIdAndUser_UserId(Long productId, Long userId);
List<CartItem> findByUser_UserId(Long userId);
Optional<CartItem> findByUser_UserIdAndProduct_ProductId(Long userId, Long productId);
}

