package com.boboibo.mytimestore.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long orderDetailId;
    @ManyToOne
    @JoinColumn(name = "order_id")
    Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;

    int quantity;
    double basePrice;
    double deliveryPrice;

//    @OneToOne(fetch = FetchType.LAZY,mappedBy = "orderDetail", cascade = CascadeType.ALL)
//    Feedback feedback; // Liên kết với Feedback
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "orderDetail", cascade = CascadeType.ALL)
    Feedback feedback;

}
