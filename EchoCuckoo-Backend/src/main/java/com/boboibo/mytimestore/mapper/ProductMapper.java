package com.boboibo.mytimestore.mapper;

import com.boboibo.mytimestore.model.entity.Product;
import com.boboibo.mytimestore.model.request.ProductRequest;
import com.boboibo.mytimestore.model.response.product.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toProduct(ProductRequest productRequest);

    ProductResponse toProductResponse(Product product);

    @Mapping(target = "productId", ignore = true) // Bỏ qua ID, không cho phép cập nhật
    void updateProductFromRequest(ProductRequest productRequest, @MappingTarget Product product);


}

