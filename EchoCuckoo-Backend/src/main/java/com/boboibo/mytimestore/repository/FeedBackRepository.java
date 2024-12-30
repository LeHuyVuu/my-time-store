package com.boboibo.mytimestore.repository;

import com.boboibo.mytimestore.model.entity.CartItem;
import com.boboibo.mytimestore.model.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedBackRepository extends JpaRepository<Feedback, Long> {
Boolean existsByOrderDetail_OrderDetailId(Long orderDetailId);
}

