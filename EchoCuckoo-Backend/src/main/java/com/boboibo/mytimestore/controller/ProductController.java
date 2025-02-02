package com.boboibo.mytimestore.controller;


import com.boboibo.mytimestore.model.entity.Product;
import com.boboibo.mytimestore.model.request.ProductRequest;
import com.boboibo.mytimestore.model.response.ResponseObject;
import com.boboibo.mytimestore.model.response.page.PageResponse;
import com.boboibo.mytimestore.model.response.product.ProductResponse;
import com.boboibo.mytimestore.service.ProductService;
import jakarta.validation.Valid;
import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Page;
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
        public ResponseEntity<ResponseObject> getAllProducts(
                @RequestParam(value = "page",required = false,defaultValue = "1") int page,
                @RequestParam(value = "pageSize",required = false,defaultValue = "10") int pageSize,
                @RequestParam(value = "sortField",required = false,defaultValue = "createdAt")String sortField,
                @RequestParam(value = "sortDirection",required = false,defaultValue = "DESC") String sortDirection) {
            PageResponse<ProductResponse> productPageResponse = productService.getAllProduct(page,pageSize,sortField,sortDirection);
            // Nếu danh sách sản phẩm rỗng, trả về thông báo không có sản phẩm
            if (productPageResponse == null) {
                return ResponseObject.APIRepsonse(404, "No products found ", HttpStatus.NOT_FOUND, null);
            }
        // Trả về danh sách sản phẩm nếu có
        return ResponseObject.APIRepsonse(200, "Products retrieved successfully", HttpStatus.OK, productPageResponse);
    }
    @GetMapping("/searchName/{productName}")
    public ResponseEntity<ResponseObject> getProductByName(
            @PathVariable String productName,
            @RequestParam(value = "page",required = false,defaultValue = "1") int page,
            @RequestParam(value = "pageSize",required = false,defaultValue = "10") int pageSize) {
        PageResponse<ProductResponse> productPageResponse = productService.getProductByName(productName,page,pageSize);
        if (productPageResponse == null) {
            return ResponseObject.APIRepsonse(404, "Product Page not found By Name", HttpStatus.NOT_FOUND, null);
        }
        return ResponseObject.APIRepsonse(200, "Product retrieved successfully", HttpStatus.OK, productPageResponse);
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
    public ResponseEntity<ResponseObject> updateProduct(@PathVariable Long id, @RequestBody ProductRequest productRequest) {
        ProductResponse updatedProduct = productService.updateProduct(id, productRequest);

        if (updatedProduct == null) {
            return ResponseObject.APIRepsonse(404, "Product not found", HttpStatus.NOT_FOUND, null);
        }

        return ResponseObject.APIRepsonse(200, "Product updated successfully", HttpStatus.OK, updatedProduct);
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
