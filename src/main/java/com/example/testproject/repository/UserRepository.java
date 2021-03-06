package com.example.testproject.repository;

import com.example.testproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByUserIdAndUsernameAndPassword(Long id, String username, String password);
    User findByUserId(Long id);
    User findByUsername(String username);
    User deleteByUsername(String username);
}
