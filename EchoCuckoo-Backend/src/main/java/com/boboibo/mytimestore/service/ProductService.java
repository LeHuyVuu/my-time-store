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
}
