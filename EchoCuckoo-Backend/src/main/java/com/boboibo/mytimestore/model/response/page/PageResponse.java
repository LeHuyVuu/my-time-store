package com.boboibo.mytimestore.model.response.page;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class PageResponse<T> {
    List<T> content ;
    int totalPages;
    int currentPage ;
    long totalElements;
    int pageSize;
}

