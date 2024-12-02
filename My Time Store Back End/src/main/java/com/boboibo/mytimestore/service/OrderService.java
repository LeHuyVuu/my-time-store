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
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Value("${myapp.api-key}")
    private String privateKey;
    @Autowired
    private CartItemRepository cartItemRepository;

    public void checkOut(OrderRequest orderRequest) {
        // Lấy người dùng từ cơ sở dữ liệu
        User user = userRepository.findUsersByUsername(orderRequest.getUsername());
        if(user == null){
            throw new RuntimeException("user not found");
        }
        Customer customer = customerRepository.findByUserUserId(user.getUserId());
        // Tạo đối tượng đơn hàng
        Order order = new Order();
        order.setOrderDate(orderRequest.getOrderDate());
        order.setTotal(orderRequest.getTotal());
        order.setCustomer(customer);

        // Lưu đơn hàng vào cơ sở dữ liệu
        orderRepository.save(order);

        List<CartItem> cartItems = orderRequest.getCartItems(); // Bạn cần phải thêm trường này vào OrderRequest



        for (CartItem cartItem : cartItems) {
            Product product = productRepository.findProductByProductName(cartItem.getProduct().getProductName());
            if(product == null){
                throw new RuntimeException("Product not found");
            }
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setProduct(product);
            orderDetail.setQuantity(cartItem.getQuantity());
            orderDetail.setBasePrice(product.getPrice());

            orderDetailRepository.save(orderDetail);
        }
        cartItems.forEach(cartItem -> {
            Product product = productRepository.findProductByProductName(cartItem.getProduct().getProductName());
            CartItem newItem = new CartItem();
            newItem.setQuantity(cartItem.getQuantity());
            newItem.setUser(user);
            newItem.setProduct(product);
            cartItemRepository.save(newItem);
        });

        cartItems.forEach(cartItem -> {
            Product product = productRepository.findProductByProductName(cartItem.getProduct().getProductName());
            product.setQuantity( product.getQuantity() - cartItem.getQuantity());
            productRepository.save(product);
        });




    }

    public List<Order> getMyOrder(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(privateKey));
        Jws<Claims> jws =  Jwts.parser() // Use parserBuilder() instead of parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
        String userId = (String) jws.getBody().get("userid");

        List<Order> list =  orderRepository.findByUser_UserId(userId);

        for (Order order : list) {
//            orderDetailRepository.findBy
        }


        return null;
    }




}

