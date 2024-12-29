package com.boboibo.mytimestore.model.response.cart;

import com.boboibo.mytimestore.model.response.product.ProductResponse;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItemResponse {
    String id;
    String productId;
    int quantity;
    String image;
    String productName;
    Long userId;
    Double price;
    ProductResponse productResponse;
}
