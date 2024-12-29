package com.boboibo.mytimestore.service;


import com.boboibo.mytimestore.exception.AppException;
import com.boboibo.mytimestore.exception.ErrorCode;
import com.boboibo.mytimestore.model.entity.*;
import com.boboibo.mytimestore.model.request.OrderRequest;
import com.boboibo.mytimestore.model.response.OrderDetailResponse;
import com.boboibo.mytimestore.model.response.OrderResponse;
import com.boboibo.mytimestore.repository.*;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class OrderService {
    OrderRepository orderRepository;
    UserService uerService;
    CartItemRepository cartItemRepository;
    CartService cartService;
    CustomerService customerService;
    private final OrderDetailRepository orderDetailRepository;

    public void checkOut(Long userId){
        User user = uerService.getUserByUserId(userId);
        Customer customer = customerService.getCustomerByUserId(userId);
        Double total =0.0;
        List<CartItem> cartItemList = cartService.cartItemRepository.findByUser_UserId(userId);
            Date now = new Date();
            Order order = new Order();
            order.setCustomer(customer);
            order.setOrderDate(now);
            order.setTotal(total);
            orderRepository.save(order);
        for (CartItem cartItem : cartItemList) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setQuantity(cartItem.getQuantity());
            orderDetail.setProduct(cartItem.getProduct());
            orderDetail.setBasePrice(cartItem.getProduct().getPrice()*cartItem.getQuantity());
            total += cartItem.getProduct().getPrice()*cartItem.getQuantity();
            orderDetail.setDeliveryPrice(100);
            orderDetail.setOrder(order);
            orderDetailRepository.save(orderDetail);
        }
        order.setTotal(total);
        orderRepository.save(order);
    }
public Order getOrderById(Long orderId){
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_EXIST));
        return order;
}
}

