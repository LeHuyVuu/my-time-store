package com.boboibo.mytimestore.repository;

import com.boboibo.mytimestore.model.entity.User;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long    > {
    User findByUsername(@Param("username") String username);
    User findByEmail(String email);
    User findByUserId(Long userId);

    boolean existsByUsername(String uniqueUsername);
}
