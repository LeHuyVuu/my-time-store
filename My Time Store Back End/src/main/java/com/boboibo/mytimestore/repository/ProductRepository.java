package com.boboibo.mytimestore.repository;

import com.boboibo.mytimestore.model.entity.Product;
import com.boboibo.mytimestore.model.enums.IsStatus;
import jakarta.validation.Valid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findByIsStatus(IsStatus isStatus);

}
