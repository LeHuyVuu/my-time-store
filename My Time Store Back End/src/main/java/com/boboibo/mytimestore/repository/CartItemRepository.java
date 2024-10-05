package com.boboibo.mytimestore.repository;

import com.boboibo.mytimestore.model.entity.CartItem;
import com.boboibo.mytimestore.model.response.CartResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CartItemRepository extends JpaRepository<CartItem, String> {

    List<CartItem> findByUser_UserId(String userId);

    List<CartItem> findByUser_UserIdAndProduct_ProductId(String userId, String productId);

    void deleteByUser_UserId(String userId);
}

