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
    String newId;
    @Lob
    @Column(name = "title", columnDefinition = "TEXT")
    String title;
    @Lob
    @Column(name = "preview", columnDefinition = "TEXT")
    String preview;
    @Lob
    @Column(name = "content", columnDefinition = "TEXT")
    String content;
    String img;
    boolean status;
}
