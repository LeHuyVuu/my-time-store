package com.boboibo.mytimestore.model.entity;

import com.boboibo.mytimestore.model.enums.Category;
import com.boboibo.mytimestore.model.enums.IsStatus;
import com.boboibo.mytimestore.model.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class    Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long productId;
    String productName;
    String image;
    Double price;
    int quantity;
    Date expiryDate;
    String description;
    int quantityInStock ;
    @Enumerated(EnumType.STRING)
    IsStatus isStatus;
    LocalDateTime createdAt;
    @Enumerated(EnumType.STRING)
    Category category;
    Double discountPrice;
    @Enumerated(EnumType.STRING)
    ProductStatus status;

    String material;               // Material of the product
    String dimensions;             // Dimensions in format (e.g., "30cm x 20cm x 10cm")
    String warranty;               // Warranty information (e.g., "1 Year")
}
