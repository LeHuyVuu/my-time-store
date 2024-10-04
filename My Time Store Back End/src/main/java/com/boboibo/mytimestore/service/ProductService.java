package com.boboibo.mytimestore.service;

import com.boboibo.mytimestore.model.entity.Product;
import com.boboibo.mytimestore.model.request.ProductRequest;
import com.boboibo.mytimestore.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductByName(String productName) {
        return productRepository.findProductByProductName(productName);
    }


    public Product updateProduct(ProductRequest newProduct, String id) {

        return productRepository.findById(id)
                .map(product1 -> {
                    product1.setProductName(newProduct.getProductName());
                    product1.setPrice(newProduct.getPrice());
                    product1.setQuantity(newProduct.getQuantity());
                    product1.setExpiryDate(newProduct.getExpiryDate());
                    product1.setImage(newProduct.getImage());
                    product1.setStatus(newProduct.isStatus());
                    return productRepository.save(product1);
                }).orElseThrow(() -> new RuntimeException("User not found"));
    }


    public boolean deleteProduct(String productName) {
        boolean check = false;
       Product product = productRepository.findProductByProductName(productName);
       if(product != null && product.isStatus()) {
           product.setStatus(false);
           productRepository.save(product);
           check = true;
       }else{
           new RuntimeException("Product not found");
           check = false;
       }
       return check;
    }

    public void insertProduct(ProductRequest newProduct) {
        Product product = new Product();
        product.setProductName(newProduct.getProductName());
        product.setPrice(newProduct.getPrice());
        product.setQuantity(newProduct.getQuantity());
        product.setExpiryDate(newProduct.getExpiryDate());
        product.setImage(newProduct.getImage());
        productRepository.save(product);
    }

    public List<Product> searchProduct(@Valid String productName) {
        return productRepository.searchByProductName(productName);
    }

    public Product getProductById(String productId) {
        return productRepository.findProductByProductId(productId);
    }
}
