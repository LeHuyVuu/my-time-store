package com.boboibo.mytimestore.model.request;

import com.boboibo.mytimestore.model.entity.CartItem;
import com.boboibo.mytimestore.model.entity.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderRequest {
    String username;  // Get from token
    double total;
    Date orderDate;

    List<CartItem> cartItems; // Thêm trường này để chứa thông tin sản phẩm
}
