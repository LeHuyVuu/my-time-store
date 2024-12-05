package com.boboibo.mytimestore.model.entity;

import com.boboibo.mytimestore.model.enums.Category;
import com.boboibo.mytimestore.model.enums.IsStatus;
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
public class Product {
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
}
