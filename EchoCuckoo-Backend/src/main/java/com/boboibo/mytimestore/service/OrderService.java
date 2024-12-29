package com.boboibo.mytimestore.service;


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
























































































//    @Autowired
//    private ProductRepository productRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private OrderRepository orderRepository;
//    @Autowired
//    private CustomerRepository customerRepository;
//
//    @Autowired
//    private OrderDetailRepository orderDetailRepository;
//
//    @Value("${myapp.api-key}")
//    private String privateKey;
//    @Autowired
//    private CartItemRepository cartItemRepository;
//
//    public void checkOut(OrderRequest orderRequest) {
//        // Lấy người dùng từ cơ sở dữ liệu
//        User user = userRepository.findUsersByUsername(orderRequest.getUsername());
//        if(user == null){
//            throw new RuntimeException("user not found");
//        }
//        Customer customer = customerRepository.findByUserUserId(user.getUserId());
//        // Tạo đối tượng đơn hàng
//        Order order = new Order();
//        order.setOrderDate(orderRequest.getOrderDate());
//        order.setTotal(orderRequest.getTotal());
//        order.setCustomer(customer);
//
//        // Lưu đơn hàng vào cơ sở dữ liệu
//        orderRepository.save(order);
//
//        List<CartItem> cartItems = orderRequest.getCartItems(); // Bạn cần phải thêm trường này vào OrderRequest
//
//
//
//        for (CartItem cartItem : cartItems) {
//            Product product = productRepository.findProductByProductName(cartItem.getProduct().getProductName());
//            if(product == null){
//                throw new RuntimeException("Product not found");
//            }
//            OrderDetail orderDetail = new OrderDetail();
//            orderDetail.setOrder(order);
//            orderDetail.setProduct(product);
//            orderDetail.setQuantity(cartItem.getQuantity());
//            orderDetail.setBasePrice(product.getPrice());
//
//            orderDetailRepository.save(orderDetail);
//        }
//        cartItems.forEach(cartItem -> {
//            Product product = productRepository.findProductByProductName(cartItem.getProduct().getProductName());
//            CartItem newItem = new CartItem();
//            newItem.setQuantity(cartItem.getQuantity());
//            newItem.setUser(user);
//            newItem.setProduct(product);
//            cartItemRepository.save(newItem);
//        });
//
//        cartItems.forEach(cartItem -> {
//            Product product = productRepository.findProductByProductName(cartItem.getProduct().getProductName());
//            product.setQuantity( product.getQuantity() - cartItem.getQuantity());
//            productRepository.save(product);
//        });
//
//
//
//
//    }
//
//    public List<Order> getMyOrder(HttpServletRequest request) {
//        String token = request.getHeader("Authorization");
//        if (token != null && token.startsWith("Bearer ")) {
//            token = token.substring(7);
//        }
//        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(privateKey));
//        Jws<Claims> jws =  Jwts.parser() // Use parserBuilder() instead of parser()
//                .setSigningKey(key)
//                .build()
//                .parseClaimsJws(token);
//        String userId = (String) jws.getBody().get("userid");
////        Customer customer = customerRepository.findByUserUserId(userId);
////
////
////        List<Order> list =  orderRepository.findByCustomer_CustomerId(userId);
////
////        for (Order order : list) {
//////            orderDetailRepository.findBy
////        }
//
//
//        return null;
//    }




}

