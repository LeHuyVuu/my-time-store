package com.boboibo.mytimestore.controller;

import com.boboibo.mytimestore.model.entity.Order;
import com.boboibo.mytimestore.model.request.OrderRequest;
import com.boboibo.mytimestore.model.response.OrderResponse;
import com.boboibo.mytimestore.model.response.ResponseObject;
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



    @PostMapping("/checkout")
    public ResponseEntity<ResponseObject> checkOut(@RequestBody OrderRequest orderRequest) {
        try {
            orderService.checkOut(orderRequest);
            return APIRepsonse("200", "Order created successfully", HttpStatus.OK, "");
        } catch (Exception e) {
            return APIRepsonse("Failed", e.getMessage(), HttpStatus.BAD_REQUEST, "");
        }
    }


    @GetMapping("/get")
    public ResponseEntity<ResponseObject> getOrder(HttpServletRequest request) {
        List<Order> list = orderService.getMyOrder(request);
        return  ResponseObject.APIRepsonse("200", "Success", HttpStatus.OK, list);

    }
}
