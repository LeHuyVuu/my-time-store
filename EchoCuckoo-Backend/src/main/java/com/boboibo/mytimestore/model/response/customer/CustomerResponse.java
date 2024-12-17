package com.boboibo.mytimestore.model.response.customer;

import com.boboibo.mytimestore.model.enums.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class CustomerResponse {
    String customerId;
    String address;
    String phone;
    String image;
}
