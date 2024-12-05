package com.boboibo.mytimestore.repository;

import com.boboibo.mytimestore.model.entity.LoggedOutToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoggedOutTokenRepository extends JpaRepository<LoggedOutToken, Long> {
    Optional<LoggedOutToken> findByToken(String token);
}
