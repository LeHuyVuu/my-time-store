package com.boboibo.mytimestore.model.entity;


import com.boboibo.mytimestore.model.RoleEnum;
import com.boboibo.mytimestore.model.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    Long userId;
    String username;
    @NotNull
    String password;
    String email;
    @Column(name = "full_name")
    String fullName;
    @Enumerated(EnumType.STRING)
    Role role;
    boolean status;
    String image;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    Customer customer;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    Staff staff;


    @Enumerated(EnumType.STRING)
    RoleEnum roles;
}
