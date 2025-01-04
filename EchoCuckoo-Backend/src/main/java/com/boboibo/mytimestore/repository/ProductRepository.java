package com.boboibo.mytimestore.repository;

import com.boboibo.mytimestore.model.entity.Product;
import com.boboibo.mytimestore.model.enums.IsStatus;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    Page<Product> findByIsStatus(IsStatus isStatus, Pageable pageable);
    Page<Product> findByProductNameContainingIgnoreCaseAndIsStatus(
            String productName,
            IsStatus isStatus,
            Pageable pageable
    );

    List<Product> findByProductNameContains(String productName);
//    List<Product> findByProductNameContains(String productName);


    Long countByProductNameContainingIgnoreCase(String productName);
}
