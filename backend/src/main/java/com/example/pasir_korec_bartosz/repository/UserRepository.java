package com.example.pasir_korec_bartosz.repository;

import com.example.pasir_korec_bartosz.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
