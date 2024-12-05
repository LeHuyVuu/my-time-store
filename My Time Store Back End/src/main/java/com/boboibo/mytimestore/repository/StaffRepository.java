package com.boboibo.mytimestore.repository;

import com.boboibo.mytimestore.model.entity.Feedback;
import com.boboibo.mytimestore.model.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {

}

