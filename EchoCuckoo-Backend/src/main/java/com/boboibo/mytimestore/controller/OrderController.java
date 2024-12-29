package com.boboibo.mytimestore.controller;

import com.boboibo.mytimestore.model.entity.Order;
import com.boboibo.mytimestore.model.request.OrderRequest;
import com.boboibo.mytimestore.model.response.OrderResponse;
import com.boboibo.mytimestore.model.response.ResponseObject;
import com.boboibo.mytimestore.model.response.cart.CartItemResponse;
import com.boboibo.mytimestore.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.boboibo.mytimestore.model.response.ResponseObject.APIRepsonse;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/{userId}")
    public ResponseEntity<ResponseObject> checkOut(@PathVariable Long userId) {
        try {
            orderService.checkOut(userId);
            return ResponseObject.APIRepsonse(200, "Check Out successfully", HttpStatus.OK, null);
        }catch (Exception e) {
            return ResponseObject.APIRepsonse(400, "Check Out failed", HttpStatus.BAD_REQUEST, e);
        }
    }
}
