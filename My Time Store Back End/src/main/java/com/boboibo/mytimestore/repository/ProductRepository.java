package com.boboibo.mytimestore.repository;

import com.boboibo.mytimestore.model.entity.Product;
import jakarta.validation.Valid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,String> {
     Product findProductByProductName(String name);


    @Query(value = "SELECT * FROM product WHERE product_name LIKE CONCAT('%', :productName, '%')", nativeQuery = true)
    List<Product> searchByProductName(@Param("productName") String productName);


     Product findProductByProductId(@Param("productId") String productId);

    Product findByProductId(String productId);
}
