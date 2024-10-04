package com.boboibo.mytimestore.model.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponse {
     String orderId;
     Date orderDate;
     Double total;
    List<OrderDetailResponse> details;


}
