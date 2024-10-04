package com.boboibo.mytimestore.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String orderDetailId;
    @ManyToOne
    @JoinColumn(name = "order_id")
    Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;

    int quantity;
    double price;


}
