package com.example.tacorebuild.repos;

import com.example.tacorebuild.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}