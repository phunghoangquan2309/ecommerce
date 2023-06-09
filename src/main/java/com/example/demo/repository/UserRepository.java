package com.example.demo.repository;

import com.example.demo.entity.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String username);
    boolean existsUserByUserName(String username);
}
