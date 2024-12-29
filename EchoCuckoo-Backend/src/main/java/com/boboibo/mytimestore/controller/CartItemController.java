package com.boboibo.mytimestore.controller;

import com.boboibo.mytimestore.exception.AppException;
import com.boboibo.mytimestore.model.entity.CartItem;
import com.boboibo.mytimestore.model.entity.Customer;
import com.boboibo.mytimestore.model.entity.Product;
import com.boboibo.mytimestore.model.request.CartItemRequest;
import com.boboibo.mytimestore.model.response.ResponseObject;
import com.boboibo.mytimestore.model.response.cart.CartItemResponse;
import com.boboibo.mytimestore.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cartItems")

public class CartItemController {
    @Autowired
    CartItemService cartItemService;

        @GetMapping("")
    public ResponseEntity<ResponseObject> getAllCartItems() {
            List<CartItemResponse> cartItemResponseList = cartItemService.getAll();
            // Nếu danh sách sản phẩm rỗng, trả về thông báo không có sản phẩm
            if (cartItemResponseList.isEmpty()) {
                return ResponseObject.APIRepsonse(404, "No cart Item found ", HttpStatus.NOT_FOUND, null);
            }
            // Trả về danh sách sản phẩm nếu có
            return ResponseObject.APIRepsonse(200, "CartItems retrieved successfully", HttpStatus.OK, cartItemResponseList);
        }
    @PostMapping("/{userId}")
    public ResponseEntity<ResponseObject> addCartItems(@PathVariable Long userId, @RequestBody CartItemRequest cartItemRequest) {
        try {
            cartItemService.createCartItems(userId, cartItemRequest);
            return ResponseObject.APIRepsonse(200, "Cart item created successfully", HttpStatus.OK, null);
        } catch (AppException e) {
            return ResponseObject.APIRepsonse(403, "Can't create cart item", HttpStatus.FORBIDDEN, null);
        }
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<ResponseObject> deleteCartItems(@PathVariable Long userId, @RequestParam Long productId) {
        try {
            cartItemService.deleteCartitem(userId, productId);
            return ResponseObject.APIRepsonse(200, "Delete Cart item created successfully", HttpStatus.OK, null);
        } catch (AppException e) {
            return ResponseObject.APIRepsonse(403, "Can't Delte cart item", HttpStatus.FORBIDDEN, null);
        }
    }
    @PutMapping("/{userId}")
    public ResponseEntity<ResponseObject> updateCartItems(@PathVariable Long userId, @RequestBody CartItemRequest cartItemRequest) {
        try {
            cartItemService.updateCartitem(userId, cartItemRequest);
            return ResponseObject.APIRepsonse(200, "Update Cart item created successfully", HttpStatus.OK, null);
        } catch (AppException e) {
            return ResponseObject.APIRepsonse(403, "Can't Update cart item", HttpStatus.FORBIDDEN, null);
        }
    }
}
