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
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String feedbackId;
    int star;
    @Lob
    @Column(name = "description", columnDefinition = "TEXT")
    String description;
    @OneToOne
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    Order order;
}