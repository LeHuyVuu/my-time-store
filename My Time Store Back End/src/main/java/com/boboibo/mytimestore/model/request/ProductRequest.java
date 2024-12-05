package com.boboibo.mytimestore.model.request;


import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequest {

    @NotNull(message = "Category cannot be null")
    String category;

    @NotNull(message = "CreatedAt cannot be null")
    LocalDateTime createdAt;

    @Size(max = 255, message = "Description must not exceed 255 characters")
    String description;

    @DecimalMin(value = "0.0", inclusive = true, message = "Discount price must be greater than or equal to 0")
    Double discountPrice;

    @NotNull(message = "Expiry date cannot be null")
    LocalDateTime expiryDate;

    @Size(max = 255, message = "Image URL must not exceed 255 characters")
    String image;

    @NotNull(message = "Status cannot be null")
    String isStatus;

    @DecimalMin(value = "0.0", inclusive = true, message = "Price must be greater than or equal to 0")
    Double price;

    @NotNull(message = "Product Name cannot be null")
    @Size(max = 255, message = "Product Name must not exceed 255 characters")
    String productName;

    @Min(value = 0, message = "Quantity must be greater than or equal to 0")
    int quantity;

    @Min(value = 0, message = "Quantity in stock must be greater than or equal to 0")
    int quantityInStock;
}
