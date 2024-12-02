package com.boboibo.mytimestore.model.entity;

import com.boboibo.mytimestore.model.enums.Category;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
    String imageId ;
    Category category ;
    @Column(name = "category_id")
    String categoryId ;
    String filePath ;
}
