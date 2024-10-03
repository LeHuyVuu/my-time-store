    package com.boboibo.mytimestore.model.entity;

    import com.fasterxml.jackson.annotation.JsonIgnore;
    import jakarta.persistence.*;
    import lombok.AccessLevel;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;
    import lombok.experimental.FieldDefaults;

    import java.util.ArrayList;
    import java.util.Date;
    import java.util.List;
    import java.util.Set;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Entity
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Table(name = "orders")  // Đổi tên bảng thành "orders"
    public class Order {
        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        String orderID;
        Date orderDate;
        Double total;

        @JsonIgnore  // This will prevent Jackson from serializing the lazy-loaded 'user
        @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id")  // Chỉ định tên cột khóa ngoại trong bảng Order
        User user;




    }
