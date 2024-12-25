package com.boboibo.mytimestore.service;

import com.boboibo.mytimestore.exception.AppException;
import com.boboibo.mytimestore.exception.ErrorCode;
import com.boboibo.mytimestore.mapper.ProductMapper;
import com.boboibo.mytimestore.model.entity.Product;
import com.boboibo.mytimestore.model.enums.IsStatus;
import com.boboibo.mytimestore.model.request.ProductRequest;
import com.boboibo.mytimestore.model.response.page.PageResponse;
import com.boboibo.mytimestore.model.response.product.ProductResponse;
import com.boboibo.mytimestore.repository.ProductRepository;
import com.boboibo.mytimestore.utils.Common;
import jakarta.validation.Valid;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private Common utils;
    public PageResponse<ProductResponse> getAllProduct(int page ,int pageSize) {
        try{
            Sort sort = Sort.by("createdAt").descending();
            Pageable pageable = PageRequest.of(page - 1 , pageSize,sort);
            var pageData = productRepository.findByIsStatus(IsStatus.ACTIVE, pageable);
            List<ProductResponse> productResponses = pageData.getContent().stream()
                    .map(productMapper::toProductResponse)
                    .collect(Collectors.toList());
            return utils.getPaginatedResponse(pageable,productResponses,pageData.getTotalElements());
        }
        catch (DataAccessException e) {
            log.error("Database error occurred while fetching active products", e);
            throw new AppException(ErrorCode.DATABASE_EXCEPTION, "Error while fetching active products");
        } catch (Exception e) {
            log.error("Unexpected error occurred", e);
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }
    }
    public PageResponse<ProductResponse> getProductByName(String productName , int page ,int pageSize) {
        try{
            Sort sort = Sort.by("createdAt").descending();
            Pageable pageable = PageRequest.of(page - 1 , pageSize,sort);
            var pageData = productRepository.findByProductNameContainingIgnoreCase(productName, pageable);
            List<ProductResponse> productResponses = pageData.getContent().stream()
                    .map(productMapper::toProductResponse)
                    .collect(Collectors.toList());
            return utils.getPaginatedResponse(pageable,productResponses,pageData.getTotalElements());
        }
        catch (DataAccessException e) {
            log.error("Database error occurred while fetching active products", e);
            throw new AppException(ErrorCode.DATABASE_EXCEPTION, "Error while fetching active products");
        } catch (Exception e) {
            log.error("Unexpected error occurred", e);
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }
    }

    public Optional<Product> getProductById(Long id) {
        try {
            Optional<Product> product = productRepository.findById(id);
            if(product.get().getIsStatus() == IsStatus.ACTIVE) {
                return product;
            }
        } catch (DataAccessException e) {
            log.error("Database error occurred while fetching product by id", e);
            throw new AppException(ErrorCode.DATABASE_EXCEPTION, "Error while fetching product by id");
        } catch (Exception e) {
            log.error("Unexpected error occurred", e);
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }
        return Optional.empty();
    }
    public boolean createProduct(@Valid ProductRequest productRequest) {
        boolean result = false;
        try{
            Product product = productMapper.toProduct(productRequest);
            productRepository.save(product);
            result = true;
        }catch(DataAccessException e){
            log.error("Database error occurred while creating product", e.getMessage());
            throw new AppException(ErrorCode.DATABASE_EXCEPTION, "Error while creating product");
        }
        return result;
    }

    public Optional<Product> updateProduct(Long id, @Valid Product product) {
        return Optional.empty();
    }

    public boolean deleteProduct(Long id) {
        return false;
    }

//    private PageResponse<ProductResponse> paginateProducts (List<ProductResponse> productResponses,int offSet ,int pageSize){
//        Pageable pageable = PageRequest.of(offSet-1, pageSize).withSort(Sort.by(Sort.Direction.DESC, "createdAt"));
//        int totalItems  = productResponses.size();
//        int totalElements = pageSize;
//        int totalPages = (totalItems + pageSize - 1) / pageSize;
//        Integer start = (int) pageable.getOffset();
//        int end = Math.min(start + pageSize, totalItems);
//        if (start > totalItems) {
//            return new PageResponse<>(new ArrayList<>(), (int) Math.ceil((double) productResponses.size() / pageSize), offSet, 0, pageSize);
//        }
//        productResponses = productResponses.subList(start, end);
//        return new PageResponse<>(productResponses,totalPages,offSet,totalItems,totalElements);
//    }

//    public Product getProductByName(String productName) {
//        return productRepository.findProductByProductName(productName);
//    }
//
//
//    public Product updateProduct(ProductRequest newProduct, String id) {
//
//        return productRepository.findById(id)
//                .map(product1 -> {
//                    product1.setProductName(newProduct.getProductName());
//                    product1.setPrice(newProduct.getPrice());
//                    product1.setQuantity(newProduct.getQuantity());
//                    return productRepository.save(product1);
//                }).orElseThrow(() -> new RuntimeException("User not found"));
//    }
//
//
//    public boolean deleteProduct(String productId) {
//        boolean check = false;
//       Product product = productRepository.findProductByProductId(productId);
//       if(product != null && product.isStatus()) {
//           product.setStatus(false);
//           productRepository.save(product);
//           check = true;
//       }else{
//           new RuntimeException("Product not found");
//           check = false;
//       }
//       return check;
//    }
//
//    public void insertProduct(ProductRequest newProduct) {
//        Product product = new Product();
//        product.setProductName(newProduct.getProductName());
//        product.setPrice(newProduct.getPrice());
//        product.setQuantity(newProduct.getQuantity());
//        product.setExpiryDate(newProduct.getExpiryDate());
//        product.setImage(newProduct.getImage());
//        productRepository.save(product);
//    }
//
//    public List<Product> searchProduct(@Valid String productName) {
//        return productRepository.searchByProductName(productName);
//    }
//
//    public Product getProductById(String productId) {
//        return productRepository.findProductByProductId(productId);
//    }
}
