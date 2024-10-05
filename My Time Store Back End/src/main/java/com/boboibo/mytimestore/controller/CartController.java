package com.boboibo.mytimestore.controller;

import com.boboibo.mytimestore.model.entity.CartItem;
import com.boboibo.mytimestore.model.entity.User;
import com.boboibo.mytimestore.model.request.CartItemRequest;
import com.boboibo.mytimestore.model.response.CartResponse;
import com.boboibo.mytimestore.model.response.ResponseObject;
import com.boboibo.mytimestore.service.CartService;
import com.boboibo.mytimestore.service.UserService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    @Autowired
    CartService cartService;

    @Autowired
    UserService userService;

    @PostMapping("/get")
    public ResponseEntity<ResponseObject> getCart(HttpServletRequest request) {
        User myInfo = userService.getMyInfo(request);
        List<CartResponse> list = cartService.getMyCart(myInfo.getUserId());
        return ResponseObject.APIRepsonse("200", "", HttpStatus.OK, list);
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseObject> addCart(HttpServletRequest request, @RequestBody CartItemRequest cartItemRequest) {
        User myInfo = userService.getMyInfo(request);
        boolean isAdded = cartService.addToCart(myInfo, cartItemRequest);
        if (isAdded) {
            return ResponseObject.APIRepsonse("200", "", HttpStatus.OK, "");
        } else
            return ResponseObject.APIRepsonse("500", "", HttpStatus.INTERNAL_SERVER_ERROR, "");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseObject> deleteItem(HttpServletRequest request, @RequestParam String productId) {
        User myInfo = userService.getMyInfo(request);
        boolean isDeleted = cartService.deleteItem(myInfo, productId);
        if (isDeleted) {
            return ResponseObject.APIRepsonse("200", "", HttpStatus.OK, "");
        } else
            return ResponseObject.APIRepsonse("500", "", HttpStatus.INTERNAL_SERVER_ERROR, "");
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseObject> updateItem(HttpServletRequest request, @RequestParam String productId, @RequestParam String quantity) {
        User myInfo = userService.getMyInfo(request);
        boolean isUpdated = cartService.updateItem(myInfo, productId, Integer.parseInt(quantity));
        if (isUpdated) {
            return ResponseObject.APIRepsonse("200", "", HttpStatus.OK, "");
        } else
            return ResponseObject.APIRepsonse("500", "", HttpStatus.INTERNAL_SERVER_ERROR, "");
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<ResponseObject> deleteAllItems(HttpServletRequest request) {
        User myInfo = userService.getMyInfo(request);
        boolean isDeleted = cartService.deleteAll(myInfo);
        if (isDeleted) {
            return ResponseObject.APIRepsonse("200", "", HttpStatus.OK, "");
        } else
            return ResponseObject.APIRepsonse("500", "", HttpStatus.INTERNAL_SERVER_ERROR, "");
    }
}
