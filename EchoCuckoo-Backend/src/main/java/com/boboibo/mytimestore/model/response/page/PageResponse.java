package com.boboibo.mytimestore.model.response.page;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Collections;
import java.util.List;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse<T> {
    int currentPage;
    int pageSize;
    int totalPages;
    long totalElements;
    @Builder.Default
    private List<T> data = Collections.emptyList();
}

