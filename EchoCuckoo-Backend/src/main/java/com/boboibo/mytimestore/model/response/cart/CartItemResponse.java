package com.boboibo.mytimestore.model.response.cart;

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
    String userId;
    Double price;
}
