package com.boboibo.mytimestore.controller;


import com.boboibo.mytimestore.model.entity.Product;
import com.boboibo.mytimestore.model.request.ProductRequest;
import com.boboibo.mytimestore.model.response.ResponseObject;
import com.boboibo.mytimestore.service.ProductService;
import jakarta.validation.Valid;
import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.boboibo.mytimestore.model.response.ResponseObject.APIRepsonse;

@RestController
@RequestMapping("/api/v1/products")
@EnableCaching
public class ProductController {
    @Autowired
    ProductService productService;
    @GetMapping()
    @Cacheable(value = "products")
    public ResponseEntity<ResponseObject> getAllProducts() {
        List<Product> productList = productService.getAllProducts();
        // Nếu danh sách sản phẩm rỗng, trả về thông báo không có sản phẩm
        if (productList.isEmpty()) {
            return ResponseObject.APIRepsonse(404, "No products found ", HttpStatus.NOT_FOUND, null);
        }

        // Trả về danh sách sản phẩm nếu có
        return ResponseObject.APIRepsonse(200, "Products retrieved successfully", HttpStatus.OK, productList);
    }

    // GET /api/v1/products/{id} - Lấy thông tin sản phẩm theo ID
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getProductById(@PathVariable Long id) {
        Optional<Product> productOptional = productService.getProductById(id);

        if (productOptional.isEmpty()) {
            return ResponseObject.APIRepsonse(404, "Product not found", HttpStatus.NOT_FOUND, null);
        }

        return ResponseObject.APIRepsonse(200, "Product retrieved successfully", HttpStatus.OK, productOptional.get());
    }

    // POST /api/v1/products - Tạo mới một sản phẩm
    @PostMapping()
    public ResponseEntity<ResponseObject> createProduct(@Valid @RequestBody ProductRequest productRequest) {
        boolean isCreated = productService.createProduct(productRequest);
        if(!isCreated) {
            return ResponseObject.APIRepsonse(409, "Product creation failed", HttpStatus.CONFLICT, null);
        }
        return ResponseObject.APIRepsonse(201, "Product created successfully", HttpStatus.CREATED, "");
    }

    // PUT /api/v1/products/{id} - Cập nhật thông tin sản phẩm
    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateProduct(@PathVariable Long id, @Valid @RequestBody Product product) {
        Optional<Product> updatedProduct = productService.updateProduct(id, product);

        if (updatedProduct.isEmpty()) {
            return ResponseObject.APIRepsonse(404, "Product not found", HttpStatus.NOT_FOUND, null);
        }

        return ResponseObject.APIRepsonse(200, "Product updated successfully", HttpStatus.OK, updatedProduct.get());
    }

    // DELETE /api/v1/products/{id} - Xóa sản phẩm theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id) {
        boolean isDeleted = productService.deleteProduct(id);

        if (!isDeleted) {
            return ResponseObject.APIRepsonse(404, "Product not found", HttpStatus.NOT_FOUND, null);
        }

        return ResponseObject.APIRepsonse(200, "Product deleted successfully", HttpStatus.OK, null);
    }

    

}
