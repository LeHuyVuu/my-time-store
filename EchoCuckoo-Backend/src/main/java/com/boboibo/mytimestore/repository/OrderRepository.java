package com.boboibo.mytimestore.repository;


import com.boboibo.mytimestore.model.entity.Order;
import com.boboibo.mytimestore.model.response.OrderResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
//    List<Order> findAllByProduct_ProductId(Long productId);
}
