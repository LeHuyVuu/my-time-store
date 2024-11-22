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
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "customer_id")
    String customerId;
    String address;
    String phone;
    String image;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    User user;
}
