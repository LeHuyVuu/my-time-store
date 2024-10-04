package com.boboibo.mytimestore.repository;

import com.boboibo.mytimestore.model.entity.User;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findUsersByUsername(@NotNull String username);


    User findByEmail(String userEmail);

    @Modifying
    @Query(value = "UPDATE User u SET u.password = :newPassword WHERE u.userID = :userId")
    void updatePassword(@Param("newPassword") String newPassword, @Param("userId") String userId);

}
