package com.boboibo.mytimestore.utils;

import com.boboibo.mytimestore.model.response.page.PageResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class Common {
    public <T> PageResponse<T> getPaginatedResponse(Pageable pageable, List<T> content, long totalElements) {
        return PageResponse.<T>builder()
                .currentPage(pageable.getPageNumber() + 1)  // Convert page number to start from 1
                .pageSize(pageable.getPageSize())
                .totalPages((int) Math.ceil((double) totalElements / pageable.getPageSize()))
                .totalElements(totalElements)
                .data(content)
                .build();
        //            return PageResponse.<ProductResponse>builder()  // Chỉ rõ kiểu trả về là ProductResponse
//                    .currentPage(page)
//                    .pageSize(pageData.getSize())
//                    .totalPages(pageData.getTotalPages())
//                    .totalElements(pageData.getTotalElements())
//                    .data(pageData.getContent().stream()
//                            .map(productMapper::toProductResponse)
//                            .collect(Collectors.toList())) // Chuyển Stream thành List
//                    .build();
    }
}
