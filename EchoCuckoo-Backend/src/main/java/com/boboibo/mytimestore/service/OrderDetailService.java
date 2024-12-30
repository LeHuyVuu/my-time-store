package com.boboibo.mytimestore.service;


import com.boboibo.mytimestore.exception.AppException;
import com.boboibo.mytimestore.exception.ErrorCode;
import com.boboibo.mytimestore.model.entity.*;
import com.boboibo.mytimestore.repository.CartItemRepository;
import com.boboibo.mytimestore.repository.OrderDetailRepository;
import com.boboibo.mytimestore.repository.OrderRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class OrderDetailService {
    OrderDetailRepository orderDetailRepository;
    public OrderDetail getOrderDetailById(Long orderDetailId){
        OrderDetail orderDetail = orderDetailRepository.findById(orderDetailId).orElseThrow((() -> new AppException(ErrorCode.ORDER_DETAIL_NOT_EXIST)));
        return orderDetail;
    }
}

