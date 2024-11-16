package com.boboibo.mytimestore.controller;


import com.boboibo.mytimestore.model.entity.Product;
import com.boboibo.mytimestore.model.request.ProductRequest;
import com.boboibo.mytimestore.model.response.ResponseObject;
import com.boboibo.mytimestore.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.boboibo.mytimestore.model.response.ResponseObject.APIRepsonse;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("")
    List<Product> getAllProducts() {
        return productService.getAllProducts();
    }


    @GetMapping("/search/{productName}")
    ResponseEntity<ResponseObject> getProductByName(@PathVariable @Valid String productName) {
        try {
            Product foundProduct = productService.getProductByName(productName);

            if (foundProduct != null) {
                return APIRepsonse("200", "Find product successfully", HttpStatus.OK, foundProduct);
            } else {
                return APIRepsonse("Failed", "Product not found", HttpStatus.NOT_FOUND, "");
            }

        } catch (Exception e) {
            throw new RuntimeException("Invalid input");
        }
    }



    // Insert new product
    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertProduct(@RequestBody @Valid ProductRequest newProduct) {
        Product existingProduct = productService.getProductByName(newProduct.getProductName());

        if (existingProduct != null) {
            return APIRepsonse("Failed", "Product already exists", HttpStatus.FOUND, "");
        } else {
            productService.insertProduct(newProduct);
            return APIRepsonse("200", "Insert product successfully", HttpStatus.OK, newProduct);
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateProduct(@PathVariable String id, @RequestBody ProductRequest newProduct) {
        try {
            Product updatedProduct = productService.updateProduct(newProduct, id);
            if (updatedProduct != null) {
                return APIRepsonse("200", "Update product successfully", HttpStatus.OK, updatedProduct);
            } else {
                return APIRepsonse("Failed", "Update failed", HttpStatus.BAD_REQUEST, "");
            }

        } catch (Exception e) {
            throw new RuntimeException("Invalid input");
        }
    }

    @DeleteMapping("/{productId}")
    ResponseEntity<ResponseObject> deleteProduct(@PathVariable String productId) {
        Product exists = productService.getProductById(productId);
        if (exists != null && exists.isStatus() ) {
            productService.deleteProduct(productId);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("200", "Delete Successfully", "")
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Failed", "Can not find", "")
            );
        }
    }


    @GetMapping("/search")
    ResponseEntity<ResponseObject> searchProduct(@RequestParam @Valid String productName) {
        try {
            List<Product> foundProduct = productService.searchProduct(productName);

            if (foundProduct != null) {
                return APIRepsonse("200", "Find product successfully", HttpStatus.OK, foundProduct);
            } else {
                return APIRepsonse("Failed", "Product not found", HttpStatus.NOT_FOUND, "");
            }

        } catch (Exception e) {
            throw new RuntimeException("Invalid input");
        }
    }
    @GetMapping("/getProduct")
    ResponseEntity<ResponseObject> getProductByProductId(@RequestParam @Valid String productId) {
        try {
            Product foundProduct = productService.getProductById(productId);

            if (foundProduct != null) {
                return APIRepsonse("200", "Find product successfully", HttpStatus.OK, foundProduct);
            } else {
                return APIRepsonse("Failed", "Product not found", HttpStatus.NOT_FOUND, "");
            }
        } catch (Exception e) {
            throw new RuntimeException("Invalid input");
        }
    }
}
