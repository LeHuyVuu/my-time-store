package com.boboibo.mytimestore.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Tự động tăng
    private Long deliveryId;  // Thay đổi thành Long nếu muốn sử dụng tự động tăng
    @Column(name = "from_place")
    float fromPlace;
    @Column(name = "to_place")
    float toPlace;
    float price;

}
