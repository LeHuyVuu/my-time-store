package com.boboibo.mytimestore.repository;

import com.boboibo.mytimestore.model.entity.CartItem;
import com.boboibo.mytimestore.model.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    News findBynewId(Long id);
}

