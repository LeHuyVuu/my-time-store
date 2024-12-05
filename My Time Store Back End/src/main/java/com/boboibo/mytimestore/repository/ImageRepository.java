package com.boboibo.mytimestore.repository;

import com.boboibo.mytimestore.model.entity.Feedback;
import com.boboibo.mytimestore.model.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

}

