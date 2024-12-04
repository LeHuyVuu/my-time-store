package com.boboibo.mytimestore.model.entity;

import com.boboibo.mytimestore.model.enums.Category;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Image {
    @Id
    @Column(name = "image_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String imageId ;
    Category category ;
    @Column(name = "category_id")
    String categoryId ;
    String filePath ;
}
