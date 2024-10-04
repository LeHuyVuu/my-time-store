package com.boboibo.mytimestore.model.entity;


import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;

@Getter
@Entity
public class LoggedOutToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 65535)
    private String token;

    private Date loggedOutAt;

    // Constructors, getters, and setters
    public LoggedOutToken() {}

    public LoggedOutToken(String token, Date loggedOutAt) {
        this.token = token;
        this.loggedOutAt = loggedOutAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setLoggedOutAt(Date loggedOutAt) {
        this.loggedOutAt = loggedOutAt;
    }
}
