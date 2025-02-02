package com.boboibo.mytimestore.model.response.product;

import com.boboibo.mytimestore.model.enums.Category;
import com.boboibo.mytimestore.model.enums.IsStatus;
import com.boboibo.mytimestore.model.enums.ProductStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
@Getter
@Setter
@Builder
public class ProductResponse {
    Long productId;
    String productName;
    String image;
    Double price;
    int quantity;
    Date expiryDate;
    String description;
    int quantityInStock ;
    IsStatus isStatus;
        LocalDateTime createdAt;
    Category category;
    Double discountPrice;
    ProductStatus status;
    String material;               // Material of the product
    String dimensions;             // Dimensions in format (e.g., "30cm x 20cm x 10cm")
    String warranty;
}
