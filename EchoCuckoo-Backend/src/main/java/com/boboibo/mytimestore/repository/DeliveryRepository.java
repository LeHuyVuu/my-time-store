package com.boboibo.mytimestore.repository;

import com.boboibo.mytimestore.model.entity.Delivery;
import com.boboibo.mytimestore.model.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

}

