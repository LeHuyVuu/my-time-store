package com.boboibo.mytimestore.controller;

import com.boboibo.mytimestore.model.entity.Product;
import com.boboibo.mytimestore.model.response.ResponseObject;
import com.boboibo.mytimestore.model.response.cart.CartItemResponse;
import com.boboibo.mytimestore.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cartItems")

public class CartItemController {
    @Autowired
    CartItemService cartItemService;

        @GetMapping("/all")
    public ResponseEntity<ResponseObject> getAllCartItems() {
            List<CartItemResponse> cartItemResponseList = cartItemService.getAll();
            // Nếu danh sách sản phẩm rỗng, trả về thông báo không có sản phẩm
            if (cartItemResponseList.isEmpty()) {
                return ResponseObject.APIRepsonse(404, "No cart Item found ", HttpStatus.NOT_FOUND, null);
            }
            // Trả về danh sách sản phẩm nếu có
            return ResponseObject.APIRepsonse(200, "CartItems retrieved successfully", HttpStatus.OK, cartItemResponseList);

        }
//}






































































//    @Autowired
//    CartService cartService;
//
//    @Autowired
//    UserService userService;
//
//    @PostMapping("/get")
//    public ResponseEntity<ResponseObject> getCart(HttpServletRequest request) {
//        User myInfo = userService.getMyInfo(request);
//        List<CartResponse> list = cartService.getMyCart(String.valueOf(myInfo.getUserId()));
//        return ResponseObject.APIRepsonse("200", "", HttpStatus.OK, list);
//    }
//
//    @PostMapping("/add")
//    public ResponseEntity<ResponseObject> addCart(HttpServletRequest request, @RequestBody CartItemRequest cartItemRequest) {
//        User myInfo = userService.getMyInfo(request);
//        boolean isAdded = cartService.addToCart(myInfo, cartItemRequest);
//        if (isAdded) {
//            return ResponseObject.APIRepsonse("200", "", HttpStatus.OK, "");
//        } else
//            return ResponseObject.APIRepsonse("500", "", HttpStatus.INTERNAL_SERVER_ERROR, "");
//    }
//
//    @DeleteMapping("/delete")
//    public ResponseEntity<ResponseObject> deleteItem(HttpServletRequest request, @RequestParam String productId) {
//        User myInfo = userService.getMyInfo(request);
//        boolean isDeleted = cartService.deleteItem(myInfo, productId);
//        if (isDeleted) {
//            return ResponseObject.APIRepsonse("200", "", HttpStatus.OK, "");
//        } else
//            return ResponseObject.APIRepsonse("500", "", HttpStatus.INTERNAL_SERVER_ERROR, "");
//    }
//
//    @PutMapping("/update")
//    public ResponseEntity<ResponseObject> updateItem(HttpServletRequest request, @RequestParam String productId, @RequestParam String quantity) {
//        User myInfo = userService.getMyInfo(request);
//        boolean isUpdated = cartService.updateItem(myInfo, productId, Integer.parseInt(quantity));
//        if (isUpdated) {
//            return ResponseObject.APIRepsonse("200", "", HttpStatus.OK, "");
//        } else
//            return ResponseObject.APIRepsonse("500", "", HttpStatus.INTERNAL_SERVER_ERROR, "");
//    }
//
//    @DeleteMapping("/deleteAll")
//    public ResponseEntity<ResponseObject> deleteAllItems(HttpServletRequest request) {
//        User myInfo = userService.getMyInfo(request);
//        boolean isDeleted = cartService.deleteAll(myInfo);
//        if (isDeleted) {
//            return ResponseObject.APIRepsonse("200", "", HttpStatus.OK, "");
//        } else
//            return ResponseObject.APIRepsonse("500", "", HttpStatus.INTERNAL_SERVER_ERROR, "");
//    }
}
