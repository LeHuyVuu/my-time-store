package com.boboibo.mytimestore.repository;


import com.boboibo.mytimestore.model.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {

//    @Query(value = "select * from MyTimeStore.order_detail where userId")
//    List<OrderDetail> getOrderDetailByUserId(String userId);

//    List<OrderDetail> getOrderDetailByOrderId(String orderID);
}