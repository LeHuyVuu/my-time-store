package com.boboibo.mytimestore.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "new_id")
    Long newId;
    @Column(name = "title", length = 65535)
    String title;
    @Column(name = "preview", length = 65535)
    String preview;
    @Column(name = "content", length = 65535)
    String content;
    String img;
    boolean status;
}
