package com.boboibo.mytimestore.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class LoggedOutToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 65535)
    private String token;

    private Date loggedOutAt;
}
